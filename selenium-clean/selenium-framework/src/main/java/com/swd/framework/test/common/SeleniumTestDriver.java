package com.swd.framework.test.common;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.List;

public interface SeleniumTestDriver {
    public WebDriver getWebDriver();
    public void start();
    public void stop();

    public void injectWebDriverCookies(List<Cookie> cookies);
}
