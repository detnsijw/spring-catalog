package org.example.springcategory.repository;

import org.example.springcategory.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
    List<Option> findAllByCategoryId(int categoryId);
}