package com.example.demotest.service;

import com.example.demotest.dto.NotificationRequest;

public interface EmailService {
    void sendEmail(NotificationRequest notificationRequest);
}
