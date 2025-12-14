package com.example.demotest.service.impl;

import com.example.demotest.dto.NotificationRequest;
import com.example.demotest.exception.BusinessException;
import com.example.demotest.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(NotificationRequest notificationRequest) {
        if (Math.random() < 0.3) {
            throw new BusinessException("SMTP error", HttpStatus.BAD_REQUEST);
        }
        System.out.println("Order id " + notificationRequest.getOrderId()
        + " has updated status " + notificationRequest.getStatus()
        + " with message " + notificationRequest.getMessage());
    }
}
