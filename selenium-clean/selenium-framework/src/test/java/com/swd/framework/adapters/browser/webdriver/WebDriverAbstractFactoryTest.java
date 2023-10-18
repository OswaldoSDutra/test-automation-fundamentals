package com.swd.framework.adapters.browser.webdriver;

import com.swd.framework.infrastructure.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverAbstractFactoryTest {
    protected WebDriver driver;
    protected WebDriverAbstractFactory factory;

    @BeforeMethod
    public void tearUp(){
        factory = new WebDriverAbstractFactory();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void ShouldReturnFirefoxDriver() {
        System.setProperty("browser","firefox");
        driver = factory.createWebDriver();
        String driverType = ((RemoteWebDriver) this.driver).getCapabilities().getBrowserName();

        Assert.assertTrue(driverType.toLowerCase().contains("firefox"));
    }

    @Test
    public void ShouldReturnChromeDriver() {
        System.setProperty("browser","chrome");
        driver = factory.createWebDriver();
        String driverType = ((RemoteWebDriver) this.driver).getCapabilities().getBrowserName();

        Assert.assertTrue(driverType.toLowerCase().contains("chrome"));
    }

    @Test
    public void ShouldReturnDefaultDriver() {

        System.clearProperty("browser");

        driver = factory.createWebDriver();
        String driverType = ((RemoteWebDriver) this.driver).getCapabilities().getBrowserName();

        String defaultBrowser = ConfigLoader.getInstance().getDefaulBrowser();

        Assert.assertTrue(driverType.toLowerCase().contains(defaultBrowser));
    }
}
