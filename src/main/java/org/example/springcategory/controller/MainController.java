package org.example.springcategory.controller;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping
    public String mainPage(Model model) {
        model.addAttribute("user", userService.getUser());
        return "main_menu";
    }
}