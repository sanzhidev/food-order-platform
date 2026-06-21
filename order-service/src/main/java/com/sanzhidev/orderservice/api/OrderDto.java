package com.sanzhidev.orderservice.api;

import com.sanzhidev.orderservice.domain.OrderItemEntity;
import com.sanzhidev.orderservice.domain.OrderStatus;

import java.math.BigDecimal;
import java.util.Set;

public record OrderDto(
        Long id,
        Long costumerId,
        String address,
        BigDecimal totalAmount,
        String courierName,
        Integer etaMinutes,
        OrderStatus orderStatus,
        Set<OrderItemEntity> orderItemEntity

) {
}
