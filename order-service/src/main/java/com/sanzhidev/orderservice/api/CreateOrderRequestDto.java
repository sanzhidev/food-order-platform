package com.sanzhidev.orderservice.api;


import java.util.Set;

public record CreateOrderRequestDto(
        Long costumerId,
        String address,
        Set<OrderItemRequestDto > items

) {
}