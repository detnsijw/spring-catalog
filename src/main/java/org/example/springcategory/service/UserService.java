package org.example.springcategory.service;

import org.example.springcategory.model.User;

public interface UserService {
    void create(User user);

    User getUser();
}
