package com.swd.framework.adapters.browser.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.LogInspector;
import org.openqa.selenium.bidi.log.ConsoleLogEntry;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ChromeDriverFactoryTest {

    protected WebDriver driver;

    @BeforeMethod
    public void tearUp(){
        WebDriverFactory driverFactory = new ChromeDriverFactory();
        driver = driverFactory.createWebDriver();;
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void ShouldReturnChromeDriver() throws ExecutionException, InterruptedException, TimeoutException {

        String driverType = ((RemoteWebDriver) this.driver).getCapabilities().getBrowserName();

        Assert.assertTrue(driverType.toLowerCase().contains("chrome"));

    }

    @Test
    public void ShouldGetBrowserLog() throws ExecutionException, InterruptedException, TimeoutException {

        try (LogInspector logInspector = new LogInspector(driver)) {
            CompletableFuture<ConsoleLogEntry> future = new CompletableFuture<>();
            logInspector.onConsoleEntry(future::complete);

            driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
            driver.findElement(By.id("consoleLog")).click();

            ConsoleLogEntry logEntry = future.get(5, TimeUnit.SECONDS);

            Assert.assertEquals("Hello, world!", logEntry.getText());
        }
    }

    @Test
    public void shouldWait(){
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofSeconds(2));

        driver.navigate().to("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.id("consoleLog")));

        el.click();
    }

    @Test
    public void shouldScroll(){
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofSeconds(2));

        driver.navigate().to("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");

        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.id("consoleLog")));

        el.click();
    }


    }
