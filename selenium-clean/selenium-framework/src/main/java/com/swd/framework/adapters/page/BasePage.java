package com.swd.framework.adapters.page;

import com.swd.framework.infrastructure.ConfigLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class BasePage {

    protected WebDriver driver;

    protected FluentWait<WebDriver> wait;
    protected TakesScreenshot screenshot;

    public BasePage(WebDriver driver){
        System.out.println("+----- DEBUG LOG -----+");
        System.out.println("Class: BasePage");
        System.out.println("Method: Constructor");
        System.out.printf("-Current thread: %d \n", Thread.currentThread().getId());
        System.out.printf("-Current driver: %s \n", ((RemoteWebDriver) driver).getCapabilities().getBrowserName());
        System.out.printf("-Using driver instance: %d \n", driver.hashCode());

        this.driver = driver;
        this.screenshot = (TakesScreenshot)driver;
        this.wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
                                          .pollingEvery(Duration.ofSeconds(2));
    }

    public Path takeScreenShot() throws IOException {
       File file = screenshot.getScreenshotAs(OutputType.FILE);

       String targetPath = "src" +
               File.separator +
               "test" + File.separator +
               "resources" + File.separator +
               UUID.randomUUID().toString() +
               ".png";

       Path p = Paths.get(targetPath);

       Files.copy(file.toPath(), p, StandardCopyOption.REPLACE_EXISTING);

       return p;
    }

    public void load(String endPoint){
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    public void waitInvisibilityOfAllElements(By selector){
        List<WebElement> elements = driver.findElements(selector);

        System.out.println("+----- INFO LOG -----+");
        System.out.println("Class: BasePage");
        System.out.println("Method: waitForOverlaysToDisappear");
        System.out.printf("-Current thread: %d \n", Thread.currentThread().getId());
        System.out.println("-Number of elements" + elements.size());

        if(!elements.isEmpty()){
            wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
            System.out.println("Element invisible");
        } else{
            System.out.println("No element not found");
        }
    }

}
