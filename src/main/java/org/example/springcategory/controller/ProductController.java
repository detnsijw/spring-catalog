package org.example.springcategory.controller;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.model.Product;
import org.example.springcategory.repository.ProductRepository;
import org.example.springcategory.service.CategoryService;
import org.example.springcategory.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductRepository productRepository;

    @GetMapping
    public String findAll(Model model,
                          @RequestParam(defaultValue = "0") int from,
                          @RequestParam(defaultValue = "" + Integer.MAX_VALUE) int to,
                          @RequestParam(required = false) Integer categoryId){
        model.addAttribute("products", productService.findAll(categoryId, from, to));
        model.addAttribute("categories", categoryService.findAll());
        return "products";
    }

    @GetMapping("{productId}")
    public String findById(
            @PathVariable int productId,
            Model model
    ) {
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        model.addAttribute("options", productService.getOptions(product));
        return "product";
    }

    @GetMapping("/create/chooseCategory")
    public String chooseCategory(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "choose_category_to_product";
    }

    @GetMapping("/create")
    public String showForm(Model model, @RequestParam(required = false) Integer categoryId) {
        if (categoryId == null) {
            return "redirect:/products/create/chooseCategory";
        }
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryService.findById(categoryId));
        return "product_create";
    }

    @GetMapping("/update/{productId}")
    public String updateForm(Model model, @PathVariable int productId) {
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        model.addAttribute("options", productService.getOptions(product));
        return "product_update";
    }

    @PostMapping("/create")
    public String create(
            @ModelAttribute Product product,
            @RequestParam Integer categoryId,
            @RequestParam List<String> valueNames,
            @RequestParam List<Integer> optionIds
    ) {
        productService.create(product, categoryId, optionIds, valueNames);
        return "redirect:/products";
    }

    @PostMapping("/update/{productId}")
    public String update(
            @PathVariable int productId,
            @RequestParam String updatedName,
            @RequestParam double updatedPrice,
            @RequestParam List<String> valueNames,
            @RequestParam List<Integer> optionIds
    ) {
        productService.update(productId, updatedName, updatedPrice, optionIds, valueNames);
        return "redirect:/products";
    }

    @GetMapping("/delete/{productId}")
    public String deleteById(@PathVariable int productId) {
        productService.deleteById(productId);
        return "redirect:/products";
    }

    @GetMapping("/sorting")
    public String sorting(Model model,
                          @RequestParam(defaultValue = "0") int from,
                          @RequestParam(defaultValue = "" + Integer.MAX_VALUE) int to,
                          @RequestParam(required = false) Integer categoryId){
        model.addAttribute("products", productService.findAll(categoryId, from, to));
        return "sorting";
    }
}