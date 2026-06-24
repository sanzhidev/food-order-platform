package com.sanzhidev.orderservice.domain;

import com.sanzhidev.api.http.order.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository <OrderItemEntity, Long> {
}
