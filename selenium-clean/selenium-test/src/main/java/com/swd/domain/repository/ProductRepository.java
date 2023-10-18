package com.swd.domain.repository;

import com.swd.domain.entity.Product;

public interface ProductRepository {

    Product getById(int id);
    Product[] getAll();
}
