package com.swd.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public abstract class WebDriverManagerConfigTest {

    protected  WebDriver driver;

    //@BeforeClass
    @Before
    public void tearUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    //@AfterClass
    @After
    public void tearDown(){
        driver.quit();
        driver =  null;
    }

    /*
        @Test
        public void should_open_browser(){
            driver.get("https://ecommerce-playground.lambdatest.io/");
        }
    */

}
