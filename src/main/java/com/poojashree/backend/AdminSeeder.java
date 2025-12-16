package com.poojashree.backend;

import com.poojashree.backend.model.User;
import com.poojashree.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSeeder {

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            try {
                if (!userRepository.existsByUsername("admin2")) {
                    User admin = new User();
                    admin.setUsername("admin2");
                    admin.setEmail("admin2@gmail.com");
                    admin.setPassword(passwordEncoder.encode("admin123"));
                    admin.setRole("admin");
                    userRepository.save(admin);
                    System.out.println("✅ ADMIN USER CREATED");
                }
            } catch (Exception e) {
                System.err.println("⚠️ AdminSeeder skipped: " + e.getMessage());
            }
        };
    }
}
