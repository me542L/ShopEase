package com.example.Shopping_Platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication

//@EnableJpaRepositories(basePackages = "com.example.Shopping_Platform.repository")
public class ShoppingPlatformApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShoppingPlatformApplication.class, args);
	}
}
