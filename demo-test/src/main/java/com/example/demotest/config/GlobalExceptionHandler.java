package com.example.demotest.config;

import com.example.demotest.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value= BusinessException.class)
    @ResponseBody
    public ApiResponse<?> handleBusinessException(BusinessException e) {
        return ApiResponse.error(e.getMessage(), e.getStatus());
    }
    @ExceptionHandler(value= Exception.class)
    @ResponseBody
    public ApiResponse<?> handleException(Exception e) {
        return ApiResponse.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
