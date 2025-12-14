package com.example.demotest.dto;

import lombok.Data;

@Data
public class LogEvent {
    private String orderId;
    private String result;
    private String message;
}