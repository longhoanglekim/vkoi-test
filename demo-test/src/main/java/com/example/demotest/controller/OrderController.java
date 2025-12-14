package com.example.demotest.controller;

import com.example.demotest.config.ApiResponse;
import com.example.demotest.dto.CreateOrderRequest;
import com.example.demotest.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ApiResponse<?> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        return ApiResponse.success(orderService.createOrder(createOrderRequest), "Order has been created!", HttpStatus.CREATED);
    }

}
