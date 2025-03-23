# **ShopEase**

## Overview

This is a web-based Online Shopping Platform built using Spring Boot, PostgreSQL, and Thymeleaf. The application allows users to browse products, add them to the cart, proceed to checkout, and securely complete orders. Also users can directly buy products. User authentication is implemented using Spring Security.

## Features

#### User Authentication & Security

- Secure login using Spring Security.

- Passwords are stored securely using BCrypt hashing.

#### Product Management

- Browse and search for products from the database.

- Each product has a name, description, price, image, and rating.

#### Shopping Cart Functionality

- Users can add/remove items from the cart.

- The total price is dynamically calculated.

#### Checkout & Order Placement

- Users can enter their address & payment details.

- "Buy Now" option for instant purchases.

## Tech Stack
**Backend**:    Java, Spring Boot

**Frontend**:   Thymeleaf, HTML, CSS

**Database**:   PostgreSQL

**Security**:   Spring Security, BCrypt Password Encoding

**ORM**:  Spring Data JPA (Hibernate)

**Build Tool**: Maven

**IDE**:  IntelliJ IDEA

## Project Structure

src/main/java/com/example/Shopping_Platform/
│── controller/  
│   ├── LoginController.java  
│   ├── ShopController.java  
│  
│── model/  
│   ├── Product.java  
│   ├── User.java  
│   ├── CartItem.java  
│  
│── repository/  
│   ├── ProductRepository.java  
│   ├── UserRepository.java  
│   ├── CartItemRepository.java  
│  
│── service/  
│   ├── ProductService.java  
│   ├── CustomUserDetailsService.java  
│  
│── security/  
│   ├── SecurityConfig.java  
│  
│── util/  
│   ├── BCryptPasswordGenerator.java  
│  
│── ShoppingPlatformApplication.java  
│  
│── resources/templates/  
│   ├── index.html  
│   ├── product.html  
│   ├── cart.html  
│   ├── checkout.html  
│   ├── login.html  
│   ├── search.html  
│   ├── buy-now.html  
│   ├── order-confirmation.html  
│   ├── order-confirmation-single-product.html  
│   ├── checkout-single-product.html  
│── application.properties (Database & Security Config)

## Setup Instructions

**Steps to Run**

 #### 1. Clone the Repository:

git clone https://github.com/your-repo/shopping-platform.git

cd shopping-platform

#### 2. Configure the Database:
- Ensure the required tables i.e. products, users, cart_item are present in the database.

#### 3. Update application.properties with your PostgreSQL credentials.

#### 4. Build & Run the Application:

mvn clean install

mvn spring-boot:run

#### 5. Access the Application:

Open http://localhost:8080/ in your browser.




