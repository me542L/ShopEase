package com.example.Shopping_Platform.service;

import com.example.Shopping_Platform.model.Seller;
import com.example.Shopping_Platform.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public Optional<Seller> findByUsername(String username) {
        return sellerRepository.findByUsername(username);
    } 


    public Optional<Seller> findById(Long id) {
        return sellerRepository.findById(id);
    }

    public Seller saveSeller(Seller seller) {
        return sellerRepository.save(seller);
    }
}
