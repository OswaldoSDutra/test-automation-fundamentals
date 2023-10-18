package com.swd.framework.adapters.page;

import com.swd.framework.test.common.BaseTestNGTestDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BasePageTest extends BaseTestNGTestDriver {

    private BasePage basePage;
    private Path pathToScreenShot;

    @Override
    public void tearUp() {

        basePage = new BasePage(getWebDriver());
        pathToScreenShot = null;
    }

    @Override
    public void tearDown() {

        if(pathToScreenShot != null && Files.exists(pathToScreenShot)) {
            try {

                Files.delete(pathToScreenShot);

            } catch (IOException ex){
                System.out.println("+----- ERROR LOG -----+");
                System.out.println("- FAIL TO DELETE SCREENSHOT");
                System.out.println(ex.toString());
            }
        }
    }

    @Test
    public void ShouldTakeScreenShot() throws IOException {
      pathToScreenShot =  basePage.takeScreenShot();
      Assert.assertTrue(Files.exists(pathToScreenShot));
    }


}
