package org.example.springcategory.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.springcategory.model.CartItem;
import org.example.springcategory.model.Product;
import org.example.springcategory.model.Role;
import org.example.springcategory.model.User;
import org.example.springcategory.repository.CartItemRepository;
import org.example.springcategory.repository.ProductRepository;
import org.example.springcategory.repository.UserRepository;
import org.example.springcategory.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public void create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return userRepository.findByLogin(authentication.getName());
    }
}