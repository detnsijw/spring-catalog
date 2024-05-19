package org.example.springcategory.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main_menu")
public class MainMenu {
    @GetMapping
    public String menu(){
        return "main_menu";
    }
}
