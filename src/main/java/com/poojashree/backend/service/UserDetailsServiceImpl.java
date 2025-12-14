package com.poojashree.backend.service;

import com.poojashree.backend.model.User;
import com.poojashree.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // 1. Try finding by Username (This fixes the Token issue)
        Optional<User> user = userRepository.findByUsername(usernameOrEmail);

        // 2. If not found, try finding by Email (This keeps Login working)
        if (user.isEmpty()) {
            user = userRepository.findByEmail(usernameOrEmail);
        }

        // 3. Return user or throw error
        return user.orElseThrow(() -> new UsernameNotFoundException("User Not Found with: " + usernameOrEmail));
    }
}