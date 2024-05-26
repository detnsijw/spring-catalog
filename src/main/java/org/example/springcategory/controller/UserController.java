package org.example.springcategory.controller;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @GetMapping
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "user_register";
    }
}
