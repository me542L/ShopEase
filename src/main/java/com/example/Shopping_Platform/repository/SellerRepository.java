package com.example.Shopping_Platform.repository;

import com.example.Shopping_Platform.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByUsername(String username);
}
