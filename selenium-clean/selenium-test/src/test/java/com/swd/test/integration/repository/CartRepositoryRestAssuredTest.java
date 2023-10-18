package com.swd.test.integration.repository;

import com.swd.port.repository.CartRepository;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartRepositoryRestAssuredTest {

    @Test
    public void shouldAddToCartWithoutAuthentication() {

        CartRepository cart = new CartRepository();

        Response result = cart.addToCart(1215, 1);;
        Cookies c = cart.getCookies();

        Assert.assertNotNull(c);
        Assert.assertNotNull(result);
    }

}
