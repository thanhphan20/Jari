package com.example.jari.notification.service;

import com.example.jari.common.exception.ResourceNotFoundException;
import com.example.jari.notification.dto.NotificationDto;
import com.example.jari.notification.entity.Notification;
import com.example.jari.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Transactional
    public NotificationDto createNotification(NotificationDto notificationDto) {
        Notification notification = Notification.builder()
                .userId(notificationDto.getUserId())
                .title(notificationDto.getTitle())
                .message(notificationDto.getMessage())
                .type(notificationDto.getType() != null ? notificationDto.getType() : "INFO")
                .read(false)
                .link(notificationDto.getLink())
                .build();

        Notification savedNotification = notificationRepository.save(notification);
        return mapToDto(savedNotification);
    }

    @Transactional(readOnly = true)
    public NotificationDto getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));
        return mapToDto(notification);
    }

    @Transactional(readOnly = true)
    public List<NotificationDto> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NotificationDto> getUnreadNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserIdAndReadFalse(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long getUnreadCountByUserId(Long userId) {
        return notificationRepository.countByUserIdAndReadFalse(userId);
    }

    @Transactional
    public NotificationDto markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));
        notification.setRead(true);
        Notification updatedNotification = notificationRepository.save(notification);
        return mapToDto(updatedNotification);
    }

    @Transactional
    public void markAllAsRead(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserIdAndReadFalse(userId);
        notifications.forEach(n -> n.setRead(true));
        notificationRepository.saveAll(notifications);
    }

    @Transactional
    public void deleteNotification(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));
        notificationRepository.delete(notification);
    }

    private NotificationDto mapToDto(Notification notification) {
        return NotificationDto.builder()
                .id(notification.getId())
                .userId(notification.getUserId())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .type(notification.getType())
                .read(notification.isRead())
                .link(notification.getLink())
                .createdAt(notification.getCreatedAt())
                .updatedAt(notification.getUpdatedAt())
                .build();
    }
}
