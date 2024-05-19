package org.example.springcategory.service;

import org.example.springcategory.model.Category;
import org.example.springcategory.model.Option;

import java.util.List;

public interface OptionService extends AbstractService<Option> {
    void create(String optionNames, Category category);

    List<Option> findAllByCategoryId(int categoryId);
}