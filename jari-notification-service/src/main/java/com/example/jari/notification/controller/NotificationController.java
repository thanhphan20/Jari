package com.example.jari.notification.controller;

import com.example.jari.common.dto.ResponseDto;
import com.example.jari.notification.dto.NotificationDto;
import com.example.jari.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<ResponseDto<NotificationDto>> createNotification(@Valid @RequestBody NotificationDto notificationDto) {
        NotificationDto createdNotification = notificationService.createNotification(notificationDto);
        ResponseDto<NotificationDto> response = ResponseDto.<NotificationDto>builder()
                .success(true)
                .message("Notification created successfully")
                .data(createdNotification)
                .status(HttpStatus.CREATED.value())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<NotificationDto>> getNotificationById(@PathVariable Long id) {
        NotificationDto notification = notificationService.getNotificationById(id);
        ResponseDto<NotificationDto> response = ResponseDto.<NotificationDto>builder()
                .success(true)
                .message("Notification retrieved successfully")
                .data(notification)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseDto<List<NotificationDto>>> getNotificationsByUserId(@PathVariable Long userId) {
        List<NotificationDto> notifications = notificationService.getNotificationsByUserId(userId);
        ResponseDto<List<NotificationDto>> response = ResponseDto.<List<NotificationDto>>builder()
                .success(true)
                .message("Notifications retrieved successfully")
                .data(notifications)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<ResponseDto<List<NotificationDto>>> getUnreadNotificationsByUserId(@PathVariable Long userId) {
        List<NotificationDto> notifications = notificationService.getUnreadNotificationsByUserId(userId);
        ResponseDto<List<NotificationDto>> response = ResponseDto.<List<NotificationDto>>builder()
                .success(true)
                .message("Unread notifications retrieved successfully")
                .data(notifications)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/unread/count")
    public ResponseEntity<ResponseDto<Long>> getUnreadCountByUserId(@PathVariable Long userId) {
        long count = notificationService.getUnreadCountByUserId(userId);
        ResponseDto<Long> response = ResponseDto.<Long>builder()
                .success(true)
                .message("Unread count retrieved successfully")
                .data(count)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<ResponseDto<NotificationDto>> markAsRead(@PathVariable Long id) {
        NotificationDto notification = notificationService.markAsRead(id);
        ResponseDto<NotificationDto> response = ResponseDto.<NotificationDto>builder()
                .success(true)
                .message("Notification marked as read")
                .data(notification)
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/{userId}/read-all")
    public ResponseEntity<ResponseDto<Void>> markAllAsRead(@PathVariable Long userId) {
        notificationService.markAllAsRead(userId);
        ResponseDto<Void> response = ResponseDto.<Void>builder()
                .success(true)
                .message("All notifications marked as read")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        ResponseDto<Void> response = ResponseDto.<Void>builder()
                .success(true)
                .message("Notification deleted successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok(response);
    }
}
