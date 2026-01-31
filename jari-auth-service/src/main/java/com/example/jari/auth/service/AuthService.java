package com.example.jari.auth.service;

import com.example.jari.auth.dto.AuthRequest;
import com.example.jari.auth.entity.User;
import com.example.jari.auth.repository.UserRepository;
import com.example.jari.common.utils.JwtUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        
        // Send message to user-service to create profile
        rabbitTemplate.convertAndSend("user.exchange", "user.registration", user);
        
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
