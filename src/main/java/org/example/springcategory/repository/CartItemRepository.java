package org.example.springcategory.repository;

import org.example.springcategory.model.CartItem;
import org.example.springcategory.model.Product;
import org.example.springcategory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    Optional<CartItem> findByUserAndProduct(User user, Product product);

    List<CartItem> findAllByUserOrderById(User user);
}
