package org.example.springcategory.service;

import org.example.springcategory.model.Option;
import org.example.springcategory.model.Product;
import org.example.springcategory.model.Value;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    void create(Product product, int categoryId, List<Integer> optionIds, List<String> values);

    void update(int productId, String updatedName, double updatedPrice, List<Integer> optionIds, List<String> values);

    List<Product> findAll(Integer categoryId, int from, int to);

    Product findById(int id);

    void deleteById(int id);

    Map<Option, Optional<Value>> getOptions(Product product);
}