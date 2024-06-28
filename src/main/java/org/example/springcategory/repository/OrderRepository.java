package org.example.springcategory.repository;

import org.example.springcategory.model.CartItem;
import org.example.springcategory.model.Order;
import org.example.springcategory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByCartItemsAndAndUsers(CartItem cartItem, User User);
}
