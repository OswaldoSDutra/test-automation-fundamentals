package com.swd.port.testng;

import com.swd.domain.entity.Product;
import com.swd.framework.util.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProductDataProvider {

    @DataProvider(name = "allProductsDataProvider", parallel = false)
    public Object[] allProductsDataProvider() throws IOException {
        return JacksonUtils.objectFromJson("payload/product-data-source.json", Product[].class);
    }

}
