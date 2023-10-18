package com.swd.adapter.component;

import com.swd.adapter.page.StorePage;
import com.swd.framework.adapters.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PageHeader extends BasePage {

    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");

    public PageHeader(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
        return new StorePage(driver);
    }

}
