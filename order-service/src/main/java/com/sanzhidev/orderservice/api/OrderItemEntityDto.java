package com.sanzhidev.orderservice.api;

import java.math.BigDecimal;

public record OrderItemEntityDto(
        Long id,
        Long itemId,
        Integer quantity,
        BigDecimal priceAtPurchase
) {
}
