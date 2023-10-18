package com.swd.framework.adapters.browser.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class FirefoxDriverFactoryTest {

    protected WebDriver driver;

    @BeforeMethod
    public void tearUp(){
        WebDriverFactory driverFactory = new FirefoxDriverFactory();
        driver = driverFactory.createWebDriver();;
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void ShouldReturnFirefoxDriver() throws IOException {
       String driverType = ((RemoteWebDriver) this.driver).getCapabilities().getBrowserName();

       Assert.assertTrue(driverType.toLowerCase().contains("firefox"));
    }

}
