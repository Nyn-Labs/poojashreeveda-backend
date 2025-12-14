package com.poojashree.backend.controller;

import com.poojashree.backend.model.User;
import com.poojashree.backend.repository.UserRepository;
import com.poojashree.backend.security.JwtUtils;
import lombok.Data; // Ensure Lombok is working, or write getters/setters manually
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // 👇 THIS PRINT WILL SHOW US IF DATA ARRIVES
        System.out.println("🔹 DATA RECEIVED: " + loginRequest.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        User user = userRepository.findByEmail(loginRequest.getUsername()).orElseThrow();

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("role", user.getRole());
        response.put("username", user.getUsername());

        return ResponseEntity.ok(response);
    }

    // 👇 This Class defines what JSON keys we accept
    @Data
    public static class LoginRequest {
        private String username; // Matches the JSON key "username"
        private String password; // Matches the JSON key "password"
    }
}