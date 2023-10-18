package com.swd.framework.adapters.browser.webdriver;

import com.swd.framework.infrastructure.ConfigLoader;
import org.openqa.selenium.WebDriver;

public class WebDriverAbstractFactory implements WebDriverFactory {

    private final ChromeDriverFactory chromeDriverFactory;
    private final FirefoxDriverFactory firefoxDriverFactory;
    private final String defaultBrowser;

    public WebDriverAbstractFactory(){
        this.chromeDriverFactory = new ChromeDriverFactory();
        this.firefoxDriverFactory = new FirefoxDriverFactory();
        this.defaultBrowser = ConfigLoader.getInstance().getDefaulBrowser();
    }

    @Override
    public WebDriver createWebDriver() {

        String browser = System.getProperty("browser", defaultBrowser);

        switch ( DriverType.valueOf(browser.toUpperCase())){
            case CHROME:{
                return chromeDriverFactory.createWebDriver();
            }
            case FIREFOX:{
                return firefoxDriverFactory.createWebDriver();
            }
            default:
                throw new IllegalStateException("Invalid browser selected");
        }
    }
}
