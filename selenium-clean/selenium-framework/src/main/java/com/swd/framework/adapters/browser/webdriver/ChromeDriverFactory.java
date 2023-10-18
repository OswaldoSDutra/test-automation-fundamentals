package com.swd.framework.adapters.browser.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverFactory implements WebDriverFactory {
    /**
     * @return org.openqa.selenium.WebDriver;
     */
    @Override
    public WebDriver createWebDriver() {
        WebDriverManager.chromedriver().cachePath("drivers").setup();

        ChromeOptions options=new ChromeOptions();
        //options.addArguments("--headless");

        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-running-insecure-content");

        options.addArguments("--disable-gpu"); // prevent multiple processes
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model

        options.setCapability("webSocketUrl", true);

        WebDriver d = new ChromeDriver(options);
        d.manage().window().setSize(new Dimension(1920,1080));

        return d;
    }
}
