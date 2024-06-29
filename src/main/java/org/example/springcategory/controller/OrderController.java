package org.example.springcategory.controller;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String showFormPage() {
        return "order_address";
    }

    @PostMapping
    public String create(@RequestParam String address) {
        orderService.create(address);
        return "redirect:/user/orders";
    }
}