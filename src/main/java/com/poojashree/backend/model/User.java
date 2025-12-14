package com.poojashree.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id; // 👈 Don't forget this ID annotation
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Data // Generates Getters, Setters, toString, etc.
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User implements UserDetails {

    @Id // Marks this as the primary key in MongoDB
    private String id;

    private String username;
    private String email;
    private String password;
    private String fullName;
    private String role; // "admin" or "user"
    private Set<String> roles; // Optional extra roles

    // 👇 SPRING SECURITY REQUIREMENTS (Required for Login to work)

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // This tells Spring what "Role" the user has.
        // If 'role' is null, we default to "USER" to prevent crashes.
        if (role == null) {
            return Collections.singletonList(new SimpleGrantedAuthority("USER"));
        }
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 👇 These 4 methods check if the user is banned/locked.
    // We return 'true' to say "Everything is OK, let them in."

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}