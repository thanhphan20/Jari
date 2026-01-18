package com.example.jari.user.service;

import com.example.jari.common.utils.JwtUtils;
import com.example.jari.user.dto.AuthRequest;
import com.example.jari.user.entity.User;
import com.example.jari.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "user added to the system";
    }

    public String generateToken(String username) {
        return jwtUtils.generateToken(username);
    }

    public void validateToken(String token) {
        jwtUtils.validateToken(token, jwtUtils.extractUsername(token));
    }

    public String login(AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return generateToken(authRequest.getUsername());
        } else {
            throw new RuntimeException("invalid access");
        }
    }
}
