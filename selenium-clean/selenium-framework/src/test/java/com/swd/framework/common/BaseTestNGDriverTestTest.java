package com.swd.framework.common;

import com.swd.framework.adapters.browser.webdriver.WebDriverAbstractFactory;
import com.swd.framework.adapters.page.BasePage;
import com.swd.framework.test.common.BaseTestNGTestDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseTestNGDriverTestTest extends BaseTestNGTestDriver {

    private BasePage page;

    @Override
    public void tearUp() {
        page = new BasePage(getWebDriver());
    }

    @Override
    public void tearDown() {
    }

    @Test
    public void ShouldRunTearUpWithParentClass() {
        Assert.assertNotNull(page);
    }

}
