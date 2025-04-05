package com.example.Shopping_Platform.controller;

import com.example.Shopping_Platform.model.Seller;
import com.example.Shopping_Platform.model.User;
import com.example.Shopping_Platform.service.SellerService;
import com.example.Shopping_Platform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
               
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        if ("ROLE_SELLER".equals(user.getRole())) {
            Seller seller = new Seller();
            seller.setSellerUsername(user.getUsername());
            seller.setSellerPassword(user.getPassword());
            seller.setRole("ROLE_SELLER");
            sellerService.saveSeller(seller);
            return "redirect:/seller/login"; 
        } else {
            user.setRole("ROLE_USER");
            userService.save(user);
            return "redirect:/user/login";
        }
    }
}
