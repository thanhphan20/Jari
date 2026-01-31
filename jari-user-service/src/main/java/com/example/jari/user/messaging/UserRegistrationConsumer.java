package com.example.jari.user.messaging;

import com.example.jari.user.entity.User;
import com.example.jari.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationConsumer {

    private final UserRepository userRepository;

    @RabbitListener(queues = "user.registration.queue")
    public void consumeUserRegistration(User userFromAuth) {
        log.info("Received user registration from Auth Service: {}", userFromAuth.getUsername());
        
        // In a real scenario, we might want to map this to a specific Profile entity
        // For now, we update the existing User entity in user-service DB
        if (!userRepository.existsByUsername(userFromAuth.getUsername())) {
            User newUser = User.builder()
                    .username(userFromAuth.getUsername())
                    .email(userFromAuth.getEmail())
                    .password(userFromAuth.getPassword())
                    .firstName("New") // Default or get from message if provided
                    .lastName("User")
                    .active(true)
                    .build();
            userRepository.save(newUser);
            log.info("Successfully created user profile for: {}", userFromAuth.getUsername());
        }
    }
}
