package com.example.Shopping_Platform.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class SellerDetails implements UserDetails{

    private final Seller seller;

    public SellerDetails(Seller seller) {
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = seller.getRole();
        if (role == null || role.trim().isEmpty()) {
            role = "ROLE_SELLER"; // Assign a default role if none is set
        }
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return seller.getSellerPassword();
    }

    @Override
    public String getUsername() {
        return seller.getSellerUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
