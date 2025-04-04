package com.example.Shopping_Platform;

import com.example.Shopping_Platform.model.Seller;
import com.example.Shopping_Platform.service.ProductService;
import com.example.Shopping_Platform.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication


public class ShoppingPlatformApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ShoppingPlatformApplication.class, args);
	}
	@Autowired
	private ProductService productService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
		
		if (sellerService.findByUsername("default_seller") == null) {
			Seller defaultSeller = new Seller();
			defaultSeller.setSellerUsername("default_seller");
			defaultSeller.setSellerPassword(passwordEncoder.encode("default_password"));
			defaultSeller.setRole("ROLE_SELLER");
			sellerService.saveSeller(defaultSeller);
		}

		productService.updateProductsWithNullSeller();
	}
}

