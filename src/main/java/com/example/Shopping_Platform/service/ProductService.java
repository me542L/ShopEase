package com.example.Shopping_Platform.service;
import com.example.Shopping_Platform.model.Product;
import com.example.Shopping_Platform.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
