package com.swd.framework.test.common;

import com.swd.framework.adapters.browser.webdriver.WebDriverAbstractFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class BaseTestNGTestDriver implements SeleniumTestDriver {

    private final ThreadLocal<BasicTestDriver> testDriver = new ThreadLocal<>();

    @Override
    public WebDriver getWebDriver(){
        return testDriver.get().getWebDriver();
    }

    @Override
    public void injectWebDriverCookies(List<Cookie> cookies){
        testDriver.get().injectWebDriverCookies(cookies);
    }

    public abstract void tearUp();

    @Override
    public void start(){

        testDriver.set(new BasicTestDriver(new WebDriverAbstractFactory()));
        testDriver.get().start();

        tearUp();
    }

    @Parameters("browser")
    @BeforeMethod
    public void start(@Optional String browser){

        if(browser != null && !browser.isEmpty()){
            System.setProperty("browser", browser);
        }

        start();
    }

    public abstract void tearDown();

    @AfterMethod
    public void stop(ITestResult result) throws IOException {

        if(result.getStatus() == ITestResult.FAILURE){
            String browser = ((RemoteWebDriver) getWebDriver()).getCapabilities().getBrowserName();

             String fName = "incidents" +
                     File.separator +
                     browser +
                     File.separator +
                     result.getTestClass().getRealClass().getSimpleName() +
                     "_" +
                     result.getMethod().getMethodName() +
                     ".png";

            //takeScreenshot(new File(fName));
            takeScreenshotUsingAShot(new File(fName));
        }

        stop();
    }

    @Override
    public void stop(){
        tearDown();
        testDriver.get().stop();
    }

    private void takeScreenshot(File destFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getWebDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }

    private void takeScreenshotUsingAShot(File destFile){
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getWebDriver());
        try{
            ImageIO.write(screenshot.getImage(), "png", destFile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}

