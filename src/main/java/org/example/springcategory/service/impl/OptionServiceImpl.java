package org.example.springcategory.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.repository.OptionRepository;
import org.springframework.stereotype.Service;
import org.example.springcategory.model.Category;
import org.example.springcategory.model.Option;
import org.example.springcategory.repository.CategoryRepository;
import org.example.springcategory.service.OptionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Option> findAll() {
        return optionRepository.findAll();
    }

    @Override
    public List<Option> findAllByCategoryId(int categoryId) {
        return optionRepository.findAllByCategoryId(categoryId);
    }

    @Override
    public Option findById(int id) {
        return optionRepository.findById(id).orElseThrow();
    }

    @Override
    public void create(String optionNames, Category category) {
        for (String optionName : optionNames.split(", ")) {
            Option option = new Option();
            option.setName(optionName);
            option.setCategory(category);
            optionRepository.save(option);
            System.out.printf("Создана характеристика {%s}\n", optionName);
        }
    }

    @Override
    public void update(int id, Option updatedEntity) {
        // TODO
    }

    @Override
    public void deleteById(int id) {
        // TODO
    }

    // NOT IMPLEMENTED
    @Override
    public void create(Option entity) {
        throw new IllegalArgumentException();
    }
}