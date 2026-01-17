package com.example.jari.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponseDto {
    private String message;
    private int status;
    private LocalDateTime timestamp;
    private String path;
    private List<String> errors;

    // No-args constructor
    public ErrorResponseDto() {
    }

    // All-args constructor
    public ErrorResponseDto(String message, int status, LocalDateTime timestamp, String path, List<String> errors) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
        this.path = path;
        this.errors = errors;
    }
}
