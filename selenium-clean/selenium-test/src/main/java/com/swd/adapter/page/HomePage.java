package com.swd.adapter.page;

import com.swd.adapter.component.PageHeader;
import com.swd.adapter.component.ProductThumbnail;
import com.swd.framework.adapters.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        header = new PageHeader(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    private PageHeader header;
    private ProductThumbnail productThumbnail;

    public PageHeader getHeader() {
        return header;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public HomePage load(){
        super.load("/");
        return this;
    }
}
