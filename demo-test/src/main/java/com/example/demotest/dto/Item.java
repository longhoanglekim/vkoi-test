package com.example.demotest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @NotNull(message = "Product id can't be null!")
    private Long productId;
    @NotNull(message = "Quantity can't be null!")
    @Min(value = 1, message = "Quantity must be at least 1!")
    private Integer quantity;
}
