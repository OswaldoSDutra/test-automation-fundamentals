package com.swd.domain.repository;

public interface CartRepository {

    <T> T addToCart(int productId, int quantity);
    <T> T getCookies();

}
