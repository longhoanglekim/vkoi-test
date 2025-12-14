package com.example.demotest.service;


import com.example.demotest.dto.CreateOrderRequest;
import com.example.demotest.dto.OrderResponse;
import com.example.demotest.model.Order;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest createOrderRequest);
}
