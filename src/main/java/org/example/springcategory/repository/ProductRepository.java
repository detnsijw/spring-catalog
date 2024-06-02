package org.example.springcategory.repository;

import org.example.springcategory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE (:categoryId IS NULL OR p.category.id = :categoryId) " +
            "  AND p.price BETWEEN :from AND :to")
    List<Product> findAllByPriceBetween(Integer categoryId, double from, double to);
}