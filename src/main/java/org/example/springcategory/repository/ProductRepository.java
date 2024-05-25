package org.example.springcategory.repository;

import org.example.springcategory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategoryId(Integer categoryId);

    List<Product> findAllByPriceBetween(Double price1, Double price2);

    List<Product> findAllByPriceBetweenAndCategoryId(Double price1, Double price2, Integer categoryId);
}