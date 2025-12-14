package com.example.demotest.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private int status;
    public static <T> ApiResponse<T> success(T data, String message, HttpStatus status) {
        return  ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .status(status.value())
                .build();
    }

    public static <T> ApiResponse<T> success(String message, HttpStatus status) {
        return  ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .status(status.value())
                .build();
    }

    public static ApiResponse error(String message, HttpStatus status) {
        return ApiResponse.builder()
                .success(false)
                .message(message)
                .status(status.value())
                .data(null)
                .build();
    }
}
