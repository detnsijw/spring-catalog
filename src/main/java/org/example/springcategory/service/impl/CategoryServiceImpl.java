package org.example.springcategory.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.exception.BusinessLogicException;
import org.example.springcategory.model.Category;
import org.example.springcategory.model.Option;
import org.example.springcategory.repository.CategoryRepository;
import org.example.springcategory.repository.OptionRepository;
import org.example.springcategory.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final OptionRepository optionRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public void create(Category category) {
//        categoryRepository.findByName(category.getName())
//                .ifPresent(existingCategory -> {
//                    throw new BusinessLogicException("Категория с таким названием уже существует");
//                });
        categoryRepository.save(category);
    }

    @Override
    public void update(int id, Category category) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow();
        if (category.getName() != null) {
            existingCategory.setName(category.getName());
        }
        categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Option> findOptionsByCategoryId(int categoryId) {
        return optionRepository.findAllByCategoryId(categoryId);
    }
}