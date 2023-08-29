package com.swd.test;

import com.swd.infra.Config;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public abstract class ManualChromeDriverConfigTest {

    protected static WebDriver driver;

    @BeforeClass
    public static void tearUp() throws IOException {
        /*
        Download the WebDriver version corresponding to the host machine´s browser
        https://chromedriver.chromium.org/downloads
        */

            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.home") + Config.getProperty("chrome.driver.path"));

            driver = new ChromeDriver();
    }

    @AfterClass
    public static void tearDown(){
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
