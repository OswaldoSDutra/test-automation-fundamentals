package com.swd.test.integration.repository;

import com.swd.domain.entity.User;
import com.swd.framework.util.FakerUtils;
import com.swd.port.repository.AuthRepository;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRepositoryRestAssuredTest {

    @Test
    public void shouldGetHtmlToken(){
        AuthRepository r = new AuthRepository();
        String s = r.getHtmlAntiForgeryToken();

        System.out.printf("\n HTML token: %s", s);

        Assert.assertNotNull(s);
    }

    @Test
    public void shouldRegisterUser(){
        AuthRepository r = new AuthRepository();

        String username = "demouser" + new FakerUtils().generateRandomNumber();

        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");

        Response result = r.registerUser(user);
        Cookies c = r.getAuthCookies();

        Assert.assertNotNull(c);
        Assert.assertNotNull(result);
    }

}
