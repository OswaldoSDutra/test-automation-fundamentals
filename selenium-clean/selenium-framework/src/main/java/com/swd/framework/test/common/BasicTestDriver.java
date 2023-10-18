package com.swd.framework.test.common;

import com.swd.framework.adapters.browser.webdriver.WebDriverAbstractFactory;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class BasicTestDriver implements SeleniumTestDriver {

    private WebDriver driver;
    private final WebDriverAbstractFactory driverFactory;

    public BasicTestDriver(WebDriverAbstractFactory driverFactory){

        System.out.println("+----- DEBUG LOG -----+");
        System.out.println("Class: BaseDriverTest");
        System.out.println("Method: Constructor");
        System.out.printf("-Current thread: %d \n", Thread.currentThread().getId());
        this.driverFactory = driverFactory;
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    @Override
    public void start(){
        driver = driverFactory.createWebDriver();

        System.out.println("+----- DEBUG LOG -----+");
        System.out.println("Class: BaseDriverTest");
        System.out.println("Method: start");
        System.out.printf("-Current thread: %d \n", Thread.currentThread().getId());
        System.out.printf("-Created driver instance: %d \n", driver.hashCode());
        driver.manage().window().maximize();
    }

    @Override
    public void stop(){
        System.out.println("+----- DEBUG LOG -----+");
        System.out.println("Class: BaseDriverTest");
        System.out.println("Method: stop");
        System.out.printf("-Current thread: %d \n", Thread.currentThread().getId());
        System.out.printf("-Quiting driver instance: %d \n", driver.hashCode());

        driver.quit();
    }

    @Override
    public void injectWebDriverCookies(List<Cookie> cookies){
        for(Cookie c : cookies){
            driver.manage().addCookie(c);
        }
    }

}
