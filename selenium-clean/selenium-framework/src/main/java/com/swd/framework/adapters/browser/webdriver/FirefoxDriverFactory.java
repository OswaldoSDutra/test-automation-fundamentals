package com.swd.framework.adapters.browser.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverFactory implements WebDriverFactory {
    /**
     * @return org.openqa.selenium.WebDriver;
     */
    @Override
    public WebDriver createWebDriver() {
        WebDriverManager.firefoxdriver().cachePath("drivers").setup();

        FirefoxOptions options=new FirefoxOptions();
        //options.addArguments("--headless");

        //options.addArguments("--disable-gpu");

        WebDriver d = new FirefoxDriver();
        d.manage().window().setSize(new Dimension(1920,1080));

        return d;
    }
}
