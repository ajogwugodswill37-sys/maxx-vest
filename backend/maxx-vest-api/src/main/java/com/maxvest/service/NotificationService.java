package com.maxvest.service;

import com.maxvest.entity.Notification;
import java.util.List;

public interface NotificationService {
    Notification sendNotification(Long userId, Notification.NotificationType type, String title, String message);
    List<Notification> getUserNotifications(Long userId);
    void markAsRead(Long notificationId);
    Long getUnreadCount(Long userId);
}
