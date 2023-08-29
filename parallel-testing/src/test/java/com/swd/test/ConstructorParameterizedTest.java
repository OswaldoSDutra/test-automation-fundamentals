package com.swd.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class ConstructorParameterizedTest extends WebDriverManagerConfigTest {

    private String search;

    public ConstructorParameterizedTest(String search){
        this.search = search;
    }

    @Parameterized.Parameters(name = "search term = {0}")
    public static Collection<Object[]> setupData(){
        return Arrays.asList(new Object[][]{
                {"Software"},
                {"Phones"}
        });
    }

    @Test
    public void should_search_with_term(){
        driver.navigate().to("https://ecommerce-playground.lambdatest.io/");
        driver.findElement(By.name("search")).sendKeys(search);
        driver.findElement(By.cssSelector("button[type=submit][class=type-text]")).click();

    }

}
