package com.maxvest.service.impl;

import com.maxvest.entity.Notification;
import com.maxvest.entity.User;
import com.maxvest.exception.ResourceNotFoundException;
import com.maxvest.repository.NotificationRepository;
import com.maxvest.repository.UserRepository;
import com.maxvest.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Notification sendNotification(Long userId, Notification.NotificationType type, String title, String message) {
        log.info("Sending notification to user: {} type: {}", userId, type);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Notification notification = Notification.builder()
                .user(user)
                .notificationType(type)
                .title(title)
                .message(message)
                .isRead(false)
                .build();

        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        notification.setIsRead(true);
        notification.setReadAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    @Override
    public Long getUnreadCount(Long userId) {
        return notificationRepository.findByUserIdAndIsRead(userId, false).stream().count();
    }
}
