package org.example.springcategory.service;

import org.example.springcategory.model.Option;
import org.example.springcategory.model.Product;
import org.example.springcategory.model.Value;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    void create(Product product, int categoryId, List<Integer> optionIds, List<String> values);
    void delete(int categoryId);
    void update(int productId, String productName, double price, List<Integer> optionIds, List<String> values);
    Product findById(int id);
    List<Product> findAll(Integer categoryId);
    Map<Option, Optional<Value>> getOptions(Product product);

    void priceFrom(double price);
}