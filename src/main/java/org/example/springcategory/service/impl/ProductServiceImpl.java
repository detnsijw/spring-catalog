package org.example.springcategory.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.springcategory.model.Category;
import org.example.springcategory.model.Option;
import org.example.springcategory.model.Product;
import org.example.springcategory.model.Value;
import org.example.springcategory.repository.ProductRepository;
import org.example.springcategory.service.CategoryService;
import org.example.springcategory.service.ProductService;
import org.example.springcategory.repository.CategoryRepository;
import org.example.springcategory.repository.OptionRepository;
import org.example.springcategory.repository.ValueRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OptionRepository optionRepository;
    private final ValueRepository valueRepository;

    @Override
    public void create(Product product, int categoryId, List<Integer> optionIds, List<String> values) {
        // Находим категорию по id
        Category category = categoryRepository.findById(categoryId).orElseThrow();

// Устанавливаем категорию для товара
        product.setCategory(category);

// Сохраняем товар в БД
        productRepository.save(product);

// Выгружаем все Option объекты разом
        List<Option> options = optionRepository.findAllById(optionIds);

        for (int i = 0; i < optionIds.size(); i++) {
            // Достаем Option из списка
            Option option = options.get(i);

            // Достаем значение характеристики
            String valueName = values.get(i);

            // Создание объекта Value (значение характеристики)
            Value value = new Value();
            value.setName(valueName);
            value.setProduct(product);
            value.setOption(option);
            valueRepository.save(value);
        }
    }

    @Override
    public void delete(int productId) {
        valueRepository.deleteAllByProductId(productId);
        productRepository.deleteById(productId);
    }

    @Override
    public void update(int productId, String productName, double price, List<Integer> optionIds, List<String> values) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.setPrice(price);
        product.setName(productName);
        productRepository.save(product);

        for (int i = 0; i < optionIds.size(); i++) {
            int optionId = optionIds.get(i);
            Option option = optionRepository.findById(optionId).orElseThrow();
            Optional<Value> optional = valueRepository.findByProductAndOption(product, option);
            Value value;
            if(optional.isPresent()){
                value = optional.get();
            } else{
                value = new Value();
                value.setProduct(product);
                value.setOption(option);
            }
            value.setName(values.get(i));
            valueRepository.save(value);
        }
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Товар с id = " + id + " не найден!"));
    }

    @Override
    public List<Product> findAll(Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Map<Option, Optional<Value>> getOptions(Product product) {
        Map<Option, Optional<Value>> result = new LinkedHashMap<>();
        int categoryId = product.getCategory().getId();
        List<Option> options = optionRepository.findAllByCategoryId(categoryId);
        for (Option option : options) {
            Optional<Value> optionalValue = valueRepository.
                    findByProductIdAndOptionId(product.getId(), option.getId());
            result.put(option, optionalValue);
        }
        return result;
    }

    @Override
    public void priceFrom(double price) {
    }
}