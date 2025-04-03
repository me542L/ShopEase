package com.example.Shopping_Platform.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "seller")

public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getSellerId() {
        return id;
    }

    public void setSellerId(Long id) {
        this.id = id;
    }

    public String getSellerUsername() {
        return username;
    }

    public void setSellerUsername(String username) {
        this.username = username;
    }

    public String getSellerPassword() {
        return password;
    }

    public void setSellerPassword(String password) {
        this.password = password;
    }

}
