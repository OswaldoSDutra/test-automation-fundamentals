package com.swd.test;

import org.junit.Test;
import org.openqa.selenium.By;

public class BasicTwoTest extends RemoteWebDriverConfigTest{

    @Test
    public void should_search_two(){
        driver.navigate().to("https://ecommerce-playground.lambdatest.io/");
        driver.findElement(By.name("search")).sendKeys("Components");
        driver.findElement(By.cssSelector("button[type=submit][class=type-text]")).click();

    }
}
