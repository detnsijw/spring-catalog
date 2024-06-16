package org.example.springcategory.controller;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.model.Category;
import org.example.springcategory.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.springcategory.service.OptionService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final OptionService optionService;

    @GetMapping
    public String findAll(Model model){
        System.out.println("/categories");
        model.addAttribute("categories", categoryService.findAll());
        return "categories";
    }

    @GetMapping("/create")
    public String showForm(Model model){
        model.addAttribute("category", new Category());
        return "category_create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Category category, @RequestParam String optionNames){
        categoryService.create(category);
        optionService.create(optionNames, category);
        return "redirect:/categories";
    }
}