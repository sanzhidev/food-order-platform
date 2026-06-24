package com.sanzhidev.api.http.order;


import java.util.Set;

public record CreateOrderRequestDto(
        Long costumerId,
        String address,
        Set<OrderItemRequestDto> items

) {
}