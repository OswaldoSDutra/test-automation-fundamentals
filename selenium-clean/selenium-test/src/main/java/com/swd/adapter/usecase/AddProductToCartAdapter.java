package com.swd.adapter.usecase;

import com.swd.domain.usecase.AddProductToCart;
import com.swd.domain.entity.Product;
import com.swd.adapter.page.HomePage;
import com.swd.adapter.page.StorePage;

public class AddProductToCartAdapter implements AddProductToCart {

    private HomePage homePage;
    private StorePage storePage;

    public AddProductToCartAdapter(HomePage homePage){
        this.homePage = homePage;
    }

    public void addProduct(Product product)  {

        homePage.load();
        storePage = homePage.getHeader().navigateToStoreUsingMenu();
        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
    }

}
