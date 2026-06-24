package com.sanzhidev.orderservice.domain;

import com.sanzhidev.api.http.payment.PaymentMethod;

public record OrderPaymentRequest(
        Long orderId,
        PaymentMethod paymentMethod

){
}
