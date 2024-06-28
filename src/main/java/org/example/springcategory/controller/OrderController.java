package org.example.springcategory.controller;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.model.CartItem;
import org.example.springcategory.model.User;
import org.example.springcategory.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public String showAllProducts(Model model,
                                  @RequestParam(required = false) CartItem cartItem,
                                  @RequestParam(required = false) User user){
        model.addAttribute("orderProducts", orderService.showAllProducts(cartItem, user));
        return "order";
    }
}
