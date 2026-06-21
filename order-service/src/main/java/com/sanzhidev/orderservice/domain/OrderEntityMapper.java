package com.sanzhidev.orderservice.domain;

import com.sanzhidev.orderservice.api.CreateOrderRequestDto;
import com.sanzhidev.orderservice.api.OrderDto;
import org.mapstruct.*;


@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface OrderEntityMapper {


    OrderEntity toEntity(CreateOrderRequestDto orderDto);

    @AfterMapping
    default void linkOrderItemEntities(@MappingTarget OrderEntity orderEntity){
        orderEntity
                .getItems()
                .forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));

    }

    OrderDto toOrderDto(OrderEntity orderEntity);
}
