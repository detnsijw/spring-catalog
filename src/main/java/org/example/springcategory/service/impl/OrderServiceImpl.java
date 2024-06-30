package org.example.springcategory.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.model.*;
import org.example.springcategory.repository.OrderProductRepository;
import org.example.springcategory.repository.OrderRepository;
import org.example.springcategory.service.OrderService;
import org.example.springcategory.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final UserService userService;

    @Override
    public void create(String address) {
        User user = userService.getUser().orElseThrow();

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

    private String getMonthOnRus(LocalDateTime created) {
        Month month = created.getMonth();
        Locale locale = Locale.of("ru");
        return month.getDisplayName(TextStyle.SHORT, locale);
    }

    @Override
    public String getOrderDate(Order order){
        LocalDateTime created = order.getCreated();
        String fullDate = created.getDayOfMonth()
                + " " + getMonthOnRus(created)
                + " " + created.getYear() + "г."
                + " " + created.getHour() + ":" + created.getMinute();
        return fullDate;
    }
}