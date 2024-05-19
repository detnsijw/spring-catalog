package org.example.springcategory.service;

import org.example.springcategory.model.Category;
import org.example.springcategory.model.Option;

import java.util.List;

public interface CategoryService extends AbstractService<Category> {
    List<Option> findOptionsByCategoryId(int categoryId);
}