package com.example.Shopping_Platform.security;

import com.example.Shopping_Platform.repository.UserRepository;
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
import com.example.Shopping_Platform.repository.SellerRepository;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomSellerDetailsService customSellerDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) 
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/welcome", "/user/login", "/seller/login", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/seller/**").hasRole("SELLER") 
                        .requestMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated() 
                )
                .formLogin(form -> form
                        .loginPage("/user/login")
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
                        .failureHandler((request, response, exception) -> {
                            String loginType = request.getParameter("loginType");
                            if ("SELLER".equals(loginType)) {
                                response.sendRedirect("/seller/login?error=true");
                            } else {
                                response.sendRedirect("/user/login?error=true");
                            }
                        })

                        .permitAll()
        )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/welcome")
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



