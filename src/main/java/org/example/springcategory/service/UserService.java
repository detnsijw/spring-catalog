package org.example.springcategory.service;

import org.example.springcategory.model.User;
import java.util.Optional;

public interface UserService {
    void create(User user);

    Optional<User> getUser();
}
