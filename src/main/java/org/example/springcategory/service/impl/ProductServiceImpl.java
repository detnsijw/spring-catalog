package org.example.springcategory.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.springcategory.model.Category;
import org.example.springcategory.model.Option;
import org.example.springcategory.model.Product;
import org.example.springcategory.model.Value;
import org.example.springcategory.repository.CategoryRepository;
import org.example.springcategory.repository.OptionRepository;
import org.example.springcategory.repository.ProductRepository;
import org.example.springcategory.repository.ValueRepository;
import org.example.springcategory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OptionRepository optionRepository;
    private final ValueRepository valueRepository;

    @Override
    public void create(Product product, int categoryId, List<Integer> optionIds, List<String> values) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        product.setCategory(category);
        productRepository.save(product);
        List<Option> options = optionRepository.findAllById(optionIds);
        for (int i = 0; i < optionIds.size(); i++) {
            Option option = options.get(i);
            String valueName = values.get(i);
            Value value = new Value();
            value.setName(valueName);
            value.setProduct(product);
            value.setOption(option);
            valueRepository.save(value);
        }
    }

    @Override
    public void update(int productId, String updatedName, double updatedPrice, List<Integer> optionIds, List<String> values) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setPrice(updatedPrice);
        product.setName(updatedName);

        productRepository.save(product);

        for (int i = 0; i < optionIds.size(); i++) {
            int optionId = optionIds.get(i);
            Option option = optionRepository.findById(optionId).orElseThrow();
            Value value = valueRepository.findByProductAndOption(product, option)
                    .orElseGet(() -> {
                        Value newValue = new Value();
                        newValue.setProduct(product);
                        newValue.setOption(option);
                        return newValue;
                    });
            value.setName(values.get(i));
            valueRepository.save(value);
        }
    }

    @Override
    public Map<Option, Optional<Value>> getOptions(Product product) {
        Map<Option, Optional<Value>> result = new LinkedHashMap<>();

        int categoryId = product.getCategory().getId();
        List<Option> options = optionRepository.findAllByCategoryId(categoryId);
        for (Option option : options) {
            Optional<Value> optionalValue = valueRepository.findByProductAndOption(product, option);
            result.put(option, optionalValue);
        }
        return result;
    }

    @Override
    public List<Product> findAll(Integer categoryId, int from, int to) {
        return productRepository.findAllByPriceBetween(categoryId, from, to);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Товар с id=" + id + " не найден!"));
    }

    @Override
    public void deleteById(int id) {
        valueRepository.deleteAllByProductId(id);
        productRepository.deleteById(id);
    }
}