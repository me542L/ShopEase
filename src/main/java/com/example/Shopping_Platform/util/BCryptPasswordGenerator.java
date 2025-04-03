package com.example.Shopping_Platform.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "trader"; // Replace with your desired password
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}
