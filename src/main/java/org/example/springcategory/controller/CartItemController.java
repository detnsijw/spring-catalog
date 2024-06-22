package org.example.springcategory.controller;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.service.CartItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartItemController {

    private final CartItemService cartItemService;

    @GetMapping
    public String getCartPage(Model model) {
        model.addAttribute("cartItems", cartItemService.findAllCartItems());
        return "cart";
    }

    @PostMapping("/{productId}")
    public String addItemToCart(@PathVariable int productId) {
        cartItemService.addItemToCart(productId);
        return "redirect:/cart";
    }

    @GetMapping("/{id}/increase")
    public String increaseAmount(@PathVariable int id) {
        cartItemService.increaseAmount(id);
        return "redirect:/cart";
    }

    @GetMapping("/{id}/decrease")
    public String decreaseAmount(@PathVariable int id) {
        cartItemService.decreaseAmount(id);
        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable int id){
        cartItemService.deleteItemById(id);
        return "redirect:/cart";
    }

    @GetMapping("/deleteAll")
    public String deleteAll(){
        cartItemService.deleteAll();
        return "redirect:/cart";
    }
}