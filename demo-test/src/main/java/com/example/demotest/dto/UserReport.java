package com.example.demotest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserReport {
    private Long userId;
    private String name;
    private LocalDate lastLogin;
    private int activityCount;
}
