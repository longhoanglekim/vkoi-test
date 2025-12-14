package com.example.demotest.dto;

import com.example.demotest.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private String note;
}