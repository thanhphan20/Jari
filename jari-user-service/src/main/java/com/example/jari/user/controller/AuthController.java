package com.example.jari.user.controller;

import com.example.jari.user.dto.AuthRequest;
import com.example.jari.user.dto.UserDto;
import com.example.jari.user.service.AuthService;
import com.example.jari.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserDto userDto) {
        // We use UserService here which now handles password encoding in createUser
        userService.createUser(userDto);
        return "User connected to the system";
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
