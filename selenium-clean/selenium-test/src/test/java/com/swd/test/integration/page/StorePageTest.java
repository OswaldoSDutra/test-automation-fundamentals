package com.swd.test.integration.page;

import com.swd.adapter.page.StorePage;
import com.swd.domain.entity.Product;
import com.swd.framework.test.common.BaseTestNGTestDriver;
import com.swd.port.testng.ProductDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StorePageTest extends BaseTestNGTestDriver {

    @Override
    public void tearUp() {

    }

    @Override
    public void tearDown(){
    }

    @Test
    public void shouldSearchProducts() {
        StorePage storePage = new StorePage(getWebDriver());

        storePage.load();

        storePage.search("blue");
        storePage.getProductThumbnail().clickAddToCartBtn("Blue Shoes");

        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("s=blue&post_type=product"));
    }

    @Test(dataProvider = "allProductsDataProvider", dataProviderClass = ProductDataProvider.class)
    public void shouldSearchAllProducts(Product product) {
        StorePage storePage = new StorePage(getWebDriver());

        storePage.load();

        String partialName = product.getName().split(" ")[0];
        partialName = partialName.substring(0, partialName.length()/2);

        storePage.search(partialName);

        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("s="+partialName+"&post_type=product"));
    }

    @Test
    public void shouldAddAndGoToCart() {
        StorePage storePage = new StorePage(getWebDriver());

        getWebDriver().navigate().to("https://askomdch.com/store/");

        storePage.search("blue");
        storePage.getProductThumbnail().clickAddToCartBtn("Blue Shoes");

        storePage.getProductThumbnail().clickViewCart();

        Assert.assertEquals(getWebDriver().getCurrentUrl(),"https://askomdch.com/cart/");
    }

}
