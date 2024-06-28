package org.example.springcategory.service;

import org.example.springcategory.model.CartItem;
import org.example.springcategory.model.Order;
import org.example.springcategory.model.User;

import java.util.List;

public interface OrderService {
    List<Order> showAllProducts(CartItem cartItem, User user);
}
