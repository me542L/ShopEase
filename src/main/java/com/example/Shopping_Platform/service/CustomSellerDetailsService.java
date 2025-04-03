/*package com.example.Shopping_Platform.service;

import com.example.Shopping_Platform.model.Seller;
import com.example.Shopping_Platform.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomSellerDetailsService implements UserDetailsService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Seller seller = sellerRepository.findByUsername(username);
        if (seller == null) {
            throw new UsernameNotFoundException("Seller not found");
        }
        return new org.springframework.security.core.userdetails.User(seller.getSellerUsername(), seller.getSellerPassword(), new ArrayList<>());
    }
}*/
package com.example.Shopping_Platform.service;

import com.example.Shopping_Platform.model.Seller;
import com.example.Shopping_Platform.model.SellerDetails;
import com.example.Shopping_Platform.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomSellerDetailsService implements UserDetailsService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Seller seller = sellerRepository.findByUsername(username);
        if (seller == null) {
            throw new UsernameNotFoundException("Seller not found");
        }
        return new SellerDetails(seller);
    }
}

