package org.example.springcategory.controller;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.model.User;
import org.example.springcategory.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "user_register";
    }

    @PostMapping("/register")
    public String create(@ModelAttribute User user) {
        userService.create(user);
        return "redirect:/login";
    }
}