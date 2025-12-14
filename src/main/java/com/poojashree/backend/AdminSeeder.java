package com.poojashree.backend;

// 👇 THESE IMPORTS ARE CRITICAL. DO NOT DELETE THEM.
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
            // Check if admin already exists to avoid duplicates
            // If "existsByUsername" is red, hover over it. You might need to add it to UserRepository.
            // Inside the run method:
            if (!userRepository.existsByUsername("admin2")) {
                User admin = new User();
                admin.setUsername("admin2");
                admin.setEmail("admin2@gmail.com"); // 👈 USE THIS TO LOGIN
                admin.setPassword(passwordEncoder.encode("admin123")); // 👈 PASSWORD
                admin.setRole("admin");
                userRepository.save(admin);
                System.out.println("✅ FRESH ADMIN USER (admin2) CREATED!");
            }
        };
    }
}