package org.example.springcategory.service;

import org.example.springcategory.model.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItem> findAllCartItems();

    void addItemToCart(int productId);

    void increaseAmount(int cartItemId);

    void decreaseAmount(int cartItemId);

    void deleteItemById(int id);

    void deleteAll();

    double sumOfPrice();
}