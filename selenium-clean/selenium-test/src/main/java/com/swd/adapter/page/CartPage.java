package com.swd.adapter.page;

import com.swd.framework.adapters.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {

    private final By productName = By.cssSelector("td[class='product-name'] a");
    private final By checkoutBtn = By.cssSelector(".checkout-button");
    private final By cartHeading = By.cssSelector(".has-text-align-center");

    private final By txtCoupon = By.id("coupon_code");

    private final By btnApplyCoupon = By.name("apply_coupon");

    private final By lblTotal = By.xpath("//td[@data-title=\"Total\"]//bdi");

    private final By lblSubtotal = By.xpath("//td[@data-title=\"Subtotal\"]//bdi");

    private final By lblStateTax = By.xpath("//td[@data-title=\"CA State Tax\"]/span");

    private final By lblShipping = By.xpath("//*[@for=\"shipping_method_0_flat_rate1\"]//bdi");

    private final By rdbShipping = By.xpath("//*[@id=\"shipping_method\"]//input[@checked=\"checked\"]/following-sibling::*");

    /*
    @FindBy(css = "td[class='product-name'] a")
    private WebElement productName;

    @FindBy(how = How.CSS, using = ".checkout-button")
    @CacheLookup
    private WebElement checkoutBtn;
    */

    public CartPage(WebDriver driver) {
        super(driver);
        //PageFactory.initElements(driver, this);
    }

    public CartPage load(){
        super.load("/cart");
        return this;
    }

    public String getProductName(){
        return wait.until(ExpectedConditions.elementToBeClickable(productName)).getText();
    }

    public CheckoutPage checkout(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutPage(driver);
    }

    public CartPage applyCoupon(String coupon){
        wait.until(ExpectedConditions.elementToBeClickable(txtCoupon)).sendKeys(coupon);
        driver.findElement(btnApplyCoupon).click();

        return this;
    }

    public double getSubtotal(){
        String t = wait.until(ExpectedConditions.elementToBeClickable(lblSubtotal)).getText();
        t = replaceCurrency(t);
        return Double.parseDouble(t);
    }

    public double getShippingCost(){
        String t = "0";

        try{
            t = wait.until(ExpectedConditions.elementToBeClickable(rdbShipping)).getText();
        }
        catch (TimeoutException e){
            t = wait.until(ExpectedConditions.elementToBeClickable(lblShipping)).getText();
        }

        t = replaceCurrency(t);
        return Double.parseDouble(t);
    }

    public double getStateTax(){
        String t = wait.until(ExpectedConditions.elementToBeClickable(lblStateTax)).getText();
        t = replaceCurrency(t);
        return Double.parseDouble(t);
    }

    public double getTotal(){
        String t = wait.until(ExpectedConditions.elementToBeClickable(lblTotal)).getText();
        t = replaceCurrency(t);
        return Double.parseDouble(t);
    }

    private String replaceCurrency(String price){
        String result = price.replaceAll("[^\\.^\\d]","");
        return  (!result.isEmpty()) ? result : "0";
    }

}
