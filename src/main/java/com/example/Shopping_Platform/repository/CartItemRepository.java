package com.example.Shopping_Platform.repository;

import com.example.Shopping_Platform.model.CartItem;
import com.example.Shopping_Platform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteById(Long id);
    List<CartItem> findByUser(User user);
}