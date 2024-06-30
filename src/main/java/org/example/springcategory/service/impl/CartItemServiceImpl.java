package org.example.springcategory.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.springcategory.model.CartItem;
import org.example.springcategory.model.Product;
import org.example.springcategory.model.User;
import org.example.springcategory.repository.CartItemRepository;
import org.example.springcategory.repository.ProductRepository;
import org.example.springcategory.service.CartItemService;
import org.example.springcategory.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;

    @Override
    public List<CartItem> findAllCartItems() {
        User user = userService.getUser().orElseThrow();
        return cartItemRepository.findAllByUserOrderById(user);
    }

    @Override
    public void addItemToCart(int productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        User user = userService.getUser().orElseThrow();
        Optional<CartItem> optional = cartItemRepository.findByUserAndProduct(user, product);
        if (optional.isPresent()) {
            CartItem cartItem = optional.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItemRepository.save(cartItem);
        }
    }


    @Override
    public void increaseAmount(int cartItemId) {
        CartItem cartItem = getById(cartItemId);
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemRepository.save(cartItem);
    }

    @Override
    public void decreaseAmount(int cartItemId) {
        CartItem cartItem = getById(cartItemId);
        cartItem.setQuantity(cartItem.getQuantity() - 1);
        if(cartItem.getQuantity() == 0){
            cartItemRepository.deleteById(cartItemId);
        } else{
            cartItemRepository.save(cartItem);
        }
    }

    private CartItem getById(int id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Сущность не найден"));
    }

    @Override
    public void deleteItemById(int productId) {
        cartItemRepository.deleteById(productId);
    }

    @Override
    public void deleteAll() {
        User user = userService.getUser().orElseThrow();
        cartItemRepository.deleteAll(user.getCartItems());
    }

    @Override
    public double sumOfPrice() {
        User user = userService.getUser().orElseThrow();
        double sum = 0;
        for (CartItem item : user.getCartItems()) {
            sum += item.getProduct().getPrice() * item.getQuantity();
        }
        return sum;
    }
}
