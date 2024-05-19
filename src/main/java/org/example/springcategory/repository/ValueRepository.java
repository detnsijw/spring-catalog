package org.example.springcategory.repository;


import org.example.springcategory.model.Option;
import org.example.springcategory.model.Product;
import org.example.springcategory.model.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ValueRepository extends JpaRepository<Value, Integer> {
    @Transactional
    void deleteAllByProductId(int productId);

    List<Value> findAllByProductId(int productId);

    Optional<Value> findByProductIdAndOptionId(int productId, int optionId);

    Optional<Value> findByProductAndOption(Product product, Option option);
}