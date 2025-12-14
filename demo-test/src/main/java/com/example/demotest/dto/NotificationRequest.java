package com.example.demotest.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
// dummy
public class NotificationRequest {
    private String orderId;
    private String message;
    private String status; // PENDING, PROCESSING, CANCELED, TRANSFERERD
    private int retryCount;

    public void incrementRetryCount() {
        retryCount++;
    }
}
