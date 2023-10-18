package com.swd.test.integration.page;

import com.swd.adapter.page.CartPage;
import com.swd.domain.entity.Product;
import com.swd.framework.test.common.BaseTestNGTestDriver;
import com.swd.framework.util.CookieUtils;
import com.swd.port.repository.CartRepository;
import com.swd.port.repository.ProductJsonRepository;
import com.swd.port.testng.ProductDataProvider;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CartPageTest extends BaseTestNGTestDriver {


    @Override
    public void tearUp() {

    }

    @Override
    public void tearDown() {

    }

    private void freeShippingTesting(Product product){

        CartPage cartPage = new CartPage(getWebDriver()).load();

        CartRepository cart = new CartRepository();
        cart.addToCart(product.getId(), 1);

        List<Cookie> cookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(cart.getCookies());
        injectWebDriverCookies(cookies);

        cartPage.load();

        double subtotal = cartPage.getSubtotal();

        cartPage.applyCoupon("freeship");
        cartPage.load();

        double newShipping = cartPage.getShippingCost();
        double newTax = cartPage.getStateTax();

        double newCost = subtotal + newShipping + newTax;

        Assert.assertEquals(0, newShipping);
        Assert.assertEquals(cartPage.getTotal(), newCost);

    }

    @Test(dataProvider = "allProductsDataProvider", dataProviderClass = ProductDataProvider.class)
    public void shouldApplyFreeshipCouponAll(Product product) {
        this.freeShippingTesting(product);
    }

    @Test
    public void shouldApplyFreeshipCoupon() {
        Product product = new ProductJsonRepository().getById(1215);
        this.freeShippingTesting(product);
    }

    @Test
    public void shouldApplyOffcart5Coupon() {

        Product product = new ProductJsonRepository().getById(1215);

        CartPage cartPage = new CartPage(getWebDriver()).load();

        CartRepository cart = new CartRepository();
        cart.addToCart(product.getId(), 1);

        List<Cookie> cookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(cart.getCookies());
        injectWebDriverCookies(cookies);

        cartPage.load();

        double taxBefore = cartPage.getStateTax();
        double totalBefore = cartPage.getTotal();
        totalBefore -= taxBefore;

        cartPage.applyCoupon("offcart5");
        cartPage.load();

        double newTax = cartPage.getStateTax();
        double newTotal = cartPage.getTotal();
        newTotal -= newTax;

        Assert.assertEquals((totalBefore - 5), newTotal);
        Assert.assertEquals(cartPage.getTotal(), newTotal);

    }

    @Test
    public void shouldApplyOffcart25Coupon() {

        Product product = new ProductJsonRepository().getById(1215);

        CartPage cartPage = new CartPage(getWebDriver()).load();

        CartRepository cart = new CartRepository();
        cart.addToCart(product.getId(), 1);

        List<Cookie> cookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(cart.getCookies());
        injectWebDriverCookies(cookies);

        cartPage.load();

        double totalBefore = cartPage.getSubtotal();

        cartPage.applyCoupon("off25");
        cartPage.load();

        double newTax = cartPage.getStateTax();
        double newShipping = cartPage.getShippingCost();

        double discount = (totalBefore * 0.25);
        double newTotal = (totalBefore - discount);

        newTotal += newTax + newShipping;

        Assert.assertEquals(cartPage.getTotal(), newTotal);

    }

    @Test
    public void shouldAddToCart() {

        Product product = new ProductJsonRepository().getById(1215);

        CartPage cartPage = new CartPage(getWebDriver()).load();

        CartRepository cart = new CartRepository();
        cart.addToCart(product.getId(), 1);

        List<Cookie> cookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(cart.getCookies());
        injectWebDriverCookies(cookies);

        cartPage.load();

        Assert.assertTrue(cartPage.getProductName().contains(product.getName()));

    }

}
