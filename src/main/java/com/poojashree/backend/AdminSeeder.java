package com.poojashree.backend;

import com.poojashree.backend.model.User;
import com.poojashree.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

// 👇 1. ADD SLASHES HERE TO DISABLE THE FILE
// @Configuration
public class AdminSeeder {

    // 👇 2. ADD SLASHES HERE TOO
    // @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // This code is now "turned off" and won't crash the app!
            if (!userRepository.existsByUsername("admin2")) {
                User admin = new User();
                admin.setUsername("admin2");
                admin.setEmail("admin2@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("admin");
                userRepository.save(admin);
                System.out.println("✅ FRESH ADMIN USER (admin2) CREATED!");
            }
        };
    }
}package com.poojashree.backend;

import com.poojashree.backend.model.User;
import com.poojashree.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

// 👇 1. ADD SLASHES HERE TO DISABLE THE FILE
// @Configuration
public class AdminSeeder {

    // 👇 2. ADD SLASHES HERE TOO
    // @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // This code is now "turned off" and won't crash the app!
            if (!userRepository.existsByUsername("admin2")) {
                User admin = new User();
                admin.setUsername("admin2");
                admin.setEmail("admin2@gmail.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("admin");
                userRepository.save(admin);
                System.out.println("✅ FRESH ADMIN USER (admin2) CREATED!");
            }
        };
    }
}