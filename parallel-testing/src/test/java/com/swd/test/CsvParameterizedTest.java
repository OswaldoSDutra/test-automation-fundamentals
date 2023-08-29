package com.swd.test;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import java.io.IOException;

@RunWith(JUnitParamsRunner.class)
public class CsvParameterizedTest extends WebDriverManagerConfigTest{

    /*
        Tests that use JUnitParamsRunner cannot be executed by the Surefire plugin
    */

    @Test
    @FileParameters("src/test/resources/csv/search-terms.csv")
    public void should_search_with_term(String searchTerm) throws IOException {
        driver.navigate().to("https://ecommerce-playground.lambdatest.io/");
        driver.findElement(By.name("search")).sendKeys(searchTerm);
        driver.findElement(By.cssSelector("button[type=submit][class=type-text]")).click();
    }
}
