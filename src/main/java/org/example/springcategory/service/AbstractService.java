package org.example.springcategory.service;

import java.util.List;

public interface AbstractService<T> {
    List<T> findAll();

    T findById(int id);

    void create(T entity);

    void update(int id, T updatedEntity);

    void deleteById(int id);
}