package com.swd.port.repository;

import com.swd.framework.infrastructure.ConfigLoader;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CartRepository implements com.swd.domain.repository.CartRepository {

    private Cookies cookies;

    public CartRepository(){}

    public CartRepository(Cookies cookies) {
        this.cookies = cookies;
    }

    @Override
    public Response addToCart(int productId, int quantity) {

        Header header = new Header("content-type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String, Object> formParams = new HashMap<>();
        formParams.put("product_sku", "");
        formParams.put("product_id", productId);
        formParams.put("quantity", quantity);

        if(this.cookies == null){
            cookies = new Cookies();
        }

        Response response = given()
                                .baseUri(ConfigLoader.getInstance().getBaseUrl())
                                .headers(headers)
                                .formParams(formParams)
                                .cookies(cookies)
                                .log().all()
                            .when()
                                .post("/?wc-ajax=add_to_cart")
                            .then()
                                .log().all()
                                .extract()
                            .response();

        if(response.getStatusCode() != 200){
            throw new RuntimeException("Failed to add product" + productId + " to the cart" +
                    ", HTTP Status Code: " + response.getStatusCode());
        }

        this.cookies = response.getDetailedCookies();

        return response;
    }

    @Override
    public Cookies getCookies() {
        return cookies;
    }
}
