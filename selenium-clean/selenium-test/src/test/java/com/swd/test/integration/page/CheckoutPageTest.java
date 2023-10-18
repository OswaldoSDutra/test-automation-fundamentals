package com.swd.test.integration.page;

import com.swd.adapter.page.CheckoutPage;
import com.swd.domain.entity.BillingAddress;
import com.swd.domain.entity.Product;
import com.swd.domain.entity.User;
import com.swd.framework.test.common.BaseTestNGTestDriver;
import com.swd.framework.util.CookieUtils;
import com.swd.framework.util.FakerUtils;
import com.swd.framework.util.JacksonUtils;
import com.swd.port.repository.AuthRepository;
import com.swd.port.repository.CartRepository;
import com.swd.port.repository.ProductJsonRepository;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckoutPageTest extends BaseTestNGTestDriver {

    @Override
    public void tearUp() {

    }

    @Override
    public void tearDown() {

    }

    @Test
    public void shouldCheckoutWithoutLogin() throws Exception {

        Product product = new ProductJsonRepository().getById(1215);

        CheckoutPage checkoutPage = new CheckoutPage(getWebDriver());
        checkoutPage.load();

        CartRepository cart = new CartRepository();
        cart.addToCart(product.getId(), 1);

        List<Cookie> cookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(cart.getCookies());
        injectWebDriverCookies(cookies);

        checkoutPage.load();

        BillingAddress billingAddress;
        billingAddress = JacksonUtils.objectFromJson("payload/default-billing-address.json", BillingAddress.class);

        checkoutPage.setBillingAddress(billingAddress);
        checkoutPage.selectDirectBankTransfer();
        checkoutPage.placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

    }

    @Test
    public void shouldRegisterAndCheckout() throws Exception {

        String username = "demouser" + new FakerUtils().generateRandomNumber();

        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");

        AuthRepository authRepository = new AuthRepository();
        authRepository.registerUser(user);

        Product product = new ProductJsonRepository().getById(1215);

        CartRepository cart = new CartRepository(authRepository.getAuthCookies());
        cart.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getWebDriver()).load();
        List<Cookie> cookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(authRepository.getAuthCookies());
        injectWebDriverCookies(cookies);

        checkoutPage.load();

        BillingAddress billingAddress;
        billingAddress = JacksonUtils.objectFromJson("payload/default-billing-address.json", BillingAddress.class);

        checkoutPage.setBillingAddress(billingAddress);
        checkoutPage.selectDirectBankTransfer();
        checkoutPage.placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

    }

    @Test
    public void shouldLoginCheckout()throws Exception {

        String username = "demouser" + new FakerUtils().generateRandomNumber();

        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");

        AuthRepository authRepository = new AuthRepository();
        authRepository.registerUser(user);

        Product product = new ProductJsonRepository().getById(1215);

        CartRepository cart = new CartRepository();
        cart.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getWebDriver()).load();
        List<Cookie> cookies = CookieUtils.convertRestAssuredCookiesToSeleniumCookies(cart.getCookies());
        injectWebDriverCookies(cookies);

        checkoutPage.load();
        checkoutPage.clickHereToLoginLink();
        checkoutPage.login(user);

        BillingAddress billingAddress;
        billingAddress = JacksonUtils.objectFromJson("payload/default-billing-address.json", BillingAddress.class);

        checkoutPage.setBillingAddress(billingAddress);
        checkoutPage.selectDirectBankTransfer();
        checkoutPage.placeOrder();

        Assert.assertEquals(checkoutPage.getNotice(), "Thank you. Your order has been received.");

    }


}
