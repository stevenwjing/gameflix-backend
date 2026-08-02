package com.example.gameflix.service;

import com.example.gameflix.model.Member;
import com.example.gameflix.model.User;
import com.example.gameflix.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(String username, String rawPassword) {
        return registerInternal(username, rawPassword, "MEMBER", null);
    }

    public boolean registerMember(String username, String rawPassword, Member member) {
        return registerInternal(username, rawPassword, "MEMBER", member);
    }

    private boolean registerInternal(String username, String rawPassword, String role, Member member) {
        if (userRepository.existsByUsername(username)) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        user.setMember(member);
        userRepository.save(user);
        return true;
    }

    public boolean login(String username, String rawPassword) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(rawPassword, user.getPassword()))
                .orElse(false);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
