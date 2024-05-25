package org.example.springcategory.controller;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.model.Product;
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
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping
    public String findAll(Model model, Integer categoryId, Double price1, Double price2){
        model.addAttribute("products", productService.findAll(categoryId));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("products", productService.findProduct(price1, price2));
        return "products";
    }

    @GetMapping("create/chooseCategory")
    public String chooseCategory(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "choose_category_to_product";
    }

    @GetMapping("/addProduct")
    public String showForm(@RequestParam int categoryId, Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryService.findById(categoryId));
        return "product_create";
    }

    @PostMapping("/addProduct")
    public String createPost(@ModelAttribute Product product,
                             @RequestParam List<String> values,
                             @RequestParam List<Integer> optionIds,
                             @RequestParam int categoryId){
        productService.create(product, categoryId, optionIds, values );
        return "redirect:/products";
    }

    @GetMapping("/delete/{productId}")
    public String deleteForm(@PathVariable int productId){
        productService.delete(productId);
        return "redirect:/products";
    }

    @GetMapping("/update/{productId}")
    public String updateForm(Model model, @PathVariable int productId) {
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        model.addAttribute("options", productService.getOptions(product));
        return "product_update";
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

    @GetMapping("/{productId}")
    public String card(@PathVariable int productId, Model model){
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        model.addAttribute("options", productService.getOptions(product));
        return "product";
    }
}