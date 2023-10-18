package com.swd.port.repository;

import com.swd.domain.repository.ProductRepository;
import com.swd.domain.entity.Product;
import com.swd.framework.util.JacksonUtils;

import java.io.IOException;

public class ProductJsonRepository implements ProductRepository {

    public Product getById(int id) {
        Product[] products = getAll();
        Product result = null;

        for (Product p: products) {
            if(p.getId() == id) {
                result = p;
                break;
            }
        }

        return result;

    }

    public Product[] getAll()  {
        Product[] result = null;

        try {
            result = JacksonUtils.objectFromJson("payload/product-data-source.json", Product[].class);
        }
        catch (IOException ex) {
            System.out.println("+----- EXECUTION LOG -----+");
            System.out.printf(ex.toString());
        }

        return result;
    }

}
