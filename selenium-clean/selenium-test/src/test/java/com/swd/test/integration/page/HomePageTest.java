package com.swd.test.integration.page;

import com.swd.adapter.page.HomePage;
import com.swd.framework.test.common.BaseTestNGTestDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTestNGTestDriver {


    @Override
    public void tearUp() {

    }

    @Override
    public void tearDown(){
    }

    @Test
    public void shouldGoToStore() {

        HomePage homePage = new HomePage(getWebDriver());
        homePage.load();

        homePage.getHeader().navigateToStoreUsingMenu();

        Assert.assertEquals(getWebDriver().getCurrentUrl(),"https://askomdch.com/store/");
    }

}
