package com.swd.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RemoteWebDriverConfigTest {
    protected RemoteWebDriver driver;

    @Before
    public void tearUp() throws IOException {

        WebDriverManager.chromedriver().setup();

        String user = System.getenv("SAUCE_USER");

        Map<String, String> options = new HashMap<>();
        options.put("username", System.getenv("SAUCE_USER"));
        options.put("accessKey", System.getenv("SAUCE_KEY"));
        options.put("build", "selenium-build-G8JQF");
        options.put("name", "RemoteSuit");

        ChromeOptions opt = new ChromeOptions();
        opt.setPlatformName("Windows 11");
        opt.setBrowserVersion("latest");

        opt.setCapability("sauce:options", options);

        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        driver = new RemoteWebDriver(url,opt);
    }

    @After
    public void tearDown(){
        driver.quit();
        driver =  null;
    }
}
