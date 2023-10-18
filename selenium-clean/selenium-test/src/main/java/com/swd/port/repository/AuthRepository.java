package com.swd.port.repository;

import com.swd.domain.entity.User;
import com.swd.framework.infrastructure.ConfigLoader;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class AuthRepository implements com.swd.domain.repository.AuthRepository {

    private Cookies cookies;

    @Override
    public Cookies getAuthCookies(){ return cookies; }

    @Override
    public Response registerUser(User user){
        Cookies cookies = new Cookies();

        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("username", user.getUsername());
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        formParams.put("woocommerce-register-nonce", getHtmlAntiForgeryToken());
        formParams.put("register", "Register");

        Response response = given()
                                .baseUri(ConfigLoader.getInstance().getBaseUrl())
                                .headers(headers)
                                .formParams(formParams)
                                .cookies(cookies)
                                .log().all()
                            .when()
                                .post("/account")
                            .then()
                                .log().all()
                                .extract()
                                .response();

        if(response.getStatusCode() != 302){
            throw new RuntimeException("Failed to register the account, HTTP Status Code: " + response.getStatusCode());
        }

        this.cookies = response.getDetailedCookies();

        return response;
    }

    public String getHtmlAntiForgeryToken(){

        Response result = getHtmlAccountPage();

        return result.htmlPath().getString("**.findAll {it.@name == \"woocommerce-register-nonce\" }.@value");
    }

    private Response getHtmlAccountPage(){

        cookies = new Cookies();

        Response r = given()
                    .baseUri(ConfigLoader.getInstance().getBaseUrl())
                    .cookies(cookies)
                    .log().all()
                .when()
                    .get("/account")
                .then()
                    .log().all()
                    .extract().response();

        if(r.getStatusCode() != 200)
            throw new RuntimeException("Failed to fetch account, HTTP status code: " + r.getStatusCode());

        return r;
    }

}
