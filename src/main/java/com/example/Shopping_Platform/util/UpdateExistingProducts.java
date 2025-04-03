/*package com.example.Shopping_Platform.util;

import com.example.Shopping_Platform.model.Product;
import com.example.Shopping_Platform.model.Seller;
import com.example.Shopping_Platform.repository.ProductRepository;
import com.example.Shopping_Platform.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateExistingProducts implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public void run(String... args) throws Exception {
        // Fetch all existing products
        List<Product> products = productRepository.findAll();

        // Fetch or create a default seller
        Seller defaultSeller = sellerRepository.findByUsername("defaultSeller");
        if (defaultSeller == null) {
            defaultSeller = new Seller();
            defaultSeller.setSellerUsername("defaultSeller");
            defaultSeller.setSellerPassword("password"); // Set a secure password

            sellerRepository.save(defaultSeller);
        }

        // Update each product to have the default seller if it doesn't already have one
        for (Product product : products) {
            if (product.getSeller() == null) {
                try {
                    product.setSeller(defaultSeller);
                    productRepository.save(product);
                } catch (ObjectOptimisticLockingFailureException e) {
                    // Handle the optimistic locking exception
                    System.out.println("Optimistic locking failure for product ID: " + product.getId());
                }
            }
        }
    }
}*/
