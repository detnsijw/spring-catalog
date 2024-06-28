package org.example.springcategory.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.model.CartItem;
import org.example.springcategory.model.User;
import org.example.springcategory.model.Order;
import org.example.springcategory.repository.OrderRepository;
import org.example.springcategory.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<Order> showAllProducts(CartItem cartItem, User user) {
        return orderRepository.findAllByCartItemsAndAndUsers(cartItem, user);
    }
}
