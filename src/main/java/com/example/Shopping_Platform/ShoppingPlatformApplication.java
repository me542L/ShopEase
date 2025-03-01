package com.example.Shopping_Platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.Shopping_Platform.model") // Scan for entity classes
@EnableJpaRepositories(basePackages = "com.example.Shopping_Platform.repository") // Scan for repositories
public class ShoppingPlatformApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShoppingPlatformApplication.class, args);
	}
}
