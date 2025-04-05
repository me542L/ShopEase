package com.example.Shopping_Platform.service;

import com.example.Shopping_Platform.model.User;
import com.example.Shopping_Platform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

     
   public void save(User user) {
        userRepository.save(user);
    }
}
