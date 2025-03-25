package com.example.Shopping_Platform.service;
import com.example.Shopping_Platform.model.CartItem;
import com.example.Shopping_Platform.model.Product;
import com.example.Shopping_Platform.model.User;
import com.example.Shopping_Platform.repository.CartItemRepository;
import com.example.Shopping_Platform.repository.UserRepository;
import com.example.Shopping_Platform.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartItemRepository cartItemRepository;




   @Autowired
   private UserRepository userRepository;

    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    public double getTotalAmount(User user) {
        List<CartItem> cartItems = getCartItems(user);
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void addToCart(Long productId, int quantity, User user) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        CartItem cartItem = new CartItem(product, quantity,user);

        cartItemRepository.save(cartItem);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void removeFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

}
