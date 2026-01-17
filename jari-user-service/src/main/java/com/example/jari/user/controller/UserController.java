package com.example.jari.user.controller;

import com.example.jari.common.dto.ResponseDto;
import com.example.jari.user.dto.UserDto;
import com.example.jari.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto<UserDto>> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        ResponseDto<UserDto> response = ResponseDto.<UserDto>builder()
                .success(true)
                .message("User created successfully")
                .data(createdUser)
                .status(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        ResponseDto<UserDto> response = ResponseDto.<UserDto>builder()
                .success(true)
                .message("User retrieved successfully")
                .data(user)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        ResponseDto<List<UserDto>> response = ResponseDto.<List<UserDto>>builder()
                .success(true)
                .message("Users retrieved successfully")
                .data(users)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<UserDto>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        ResponseDto<UserDto> response = ResponseDto.<UserDto>builder()
                .success(true)
                .message("User updated successfully")
                .data(updatedUser)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        ResponseDto<Void> response = ResponseDto.<Void>builder()
                .success(true)
                .message("User deleted successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
