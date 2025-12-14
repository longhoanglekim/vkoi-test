package com.example.demotest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException{
    private HttpStatus status;
    public BusinessException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
