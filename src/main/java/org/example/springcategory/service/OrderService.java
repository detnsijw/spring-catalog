package org.example.springcategory.service;

import org.example.springcategory.model.Order;

import java.time.LocalDateTime;

public interface OrderService {
    void create(String address);

    String getOrderDate(Order order);
}
