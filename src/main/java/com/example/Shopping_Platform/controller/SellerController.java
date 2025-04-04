package com.example.Shopping_Platform.controller;

import com.example.Shopping_Platform.model.Product;
import com.example.Shopping_Platform.model.Seller;
import com.example.Shopping_Platform.model.SellerDetails;
import com.example.Shopping_Platform.service.ProductService;
import com.example.Shopping_Platform.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    @GetMapping("/seller/login")
    public String sellerLoginPage() {
        return "seller-login";
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @GetMapping("/seller/add-product")
    public String addProductPage(@AuthenticationPrincipal SellerDetails sellerDetails, Model model) {
        model.addAttribute("sellerId", sellerDetails.getSeller().getSellerId());
        return "add-product";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/welcome.html";
}
    @PostMapping("/seller/add-product")
    public String addProduct(@AuthenticationPrincipal SellerDetails sellerDetails,
                             @RequestParam String name, @RequestParam double price,
                             @RequestParam String description, @RequestParam String imageUrl) {
        Seller seller = sellerDetails.getSeller();
        if (seller != null) {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setImageUrl(imageUrl);
            product.setSeller(seller);
            product.setRatings("No ratings yet");

            productService.saveProduct(product);
            return "redirect:/seller/add-product?success=true";
        } else {
            return "redirect:/seller/add-product?error=true";
        }
    }
}
