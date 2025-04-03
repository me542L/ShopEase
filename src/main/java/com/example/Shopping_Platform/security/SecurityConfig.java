package com.example.Shopping_Platform.security;

import com.example.Shopping_Platform.service.CustomUserDetailsService;
import com.example.Shopping_Platform.service.CustomSellerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomSellerDetailsService customSellerDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity, enable in production
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/welcome", "/user/login", "/seller/login", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/seller/**").hasRole("SELLER") // Restrict seller-related URLs to authenticated sellers
                        .requestMatchers("/user/**").hasRole("USER") // Restrict user-related URLs to authenticated users
                        .anyRequest().authenticated() // Any other request must be authenticated
                )
                .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .successHandler((request, response, authentication) -> {
                    System.out.println("User Authorities: " + authentication.getAuthorities());
                    if (authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_SELLER"))) {
                        response.sendRedirect("/seller/add-product");
                    } else if (authentication.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                        response.sendRedirect("/products");
                    }
                })
                .failureUrl("/login?error=true")
                .permitAll()
        )

                .authenticationProvider(userAuthenticationProvider())
                .authenticationProvider(sellerAuthenticationProvider());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider userAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public DaoAuthenticationProvider sellerAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customSellerDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}



