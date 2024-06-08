package org.example.springcategory.service;

import org.example.springcategory.model.CartItem;
import org.example.springcategory.model.User;

import java.util.List;

public interface UserService {
    void create(User user);

    User getUser();

    List<CartItem> findAllCartItems();

    void addItemToCart(int productId);
}
