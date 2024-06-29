package org.example.springcategory.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.model.*;
import org.example.springcategory.repository.OrderProductRepository;
import org.example.springcategory.repository.OrderRepository;
import org.example.springcategory.service.OrderService;
import org.example.springcategory.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserService userService;

    @Override
    public void create(String address) {
        User user = userService.getUser();

        Order order = new Order();
        order.setAddress(address);
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setCreated(LocalDateTime.now());
        orderRepository.save(order);

        List<CartItem> cartItems = user.getCartItems();
        for (CartItem cartItem : cartItems) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(cartItem.getProduct());
            orderProduct.setQuantity(cartItem.getQuantity());
            orderProductRepository.save(orderProduct);
        }
    }
}