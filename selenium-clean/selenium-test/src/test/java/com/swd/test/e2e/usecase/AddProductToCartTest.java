package com.swd.test.e2e.usecase;

import com.swd.adapter.page.CartPage;
import com.swd.adapter.page.HomePage;
import com.swd.adapter.usecase.AddProductToCartAdapter;
import com.swd.domain.entity.Product;
import com.swd.framework.test.common.BaseTestNGTestDriver;
import com.swd.port.repository.ProductJsonRepository;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductToCartTest extends BaseTestNGTestDriver {

    @Override
    public void tearUp() {
    }

    @Override
    public void tearDown(){
    }

    @Test
    public void shouldAddProduct() {

        Product product = new ProductJsonRepository().getById(1215);

        HomePage homePage = new HomePage(getWebDriver());

        AddProductToCartAdapter useCase = new AddProductToCartAdapter(homePage);
        useCase.addProduct(product);

        CartPage cartPage = new CartPage(getWebDriver()).load();
        Assert.assertTrue(cartPage.getProductName().contains(product.getName()));
    }
}
