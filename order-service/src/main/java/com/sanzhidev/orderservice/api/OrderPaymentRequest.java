package com.sanzhidev.orderservice.api;

import com.sanzhidev.api.http.payment.PaymentMethod;

public record OrderPaymentRequest(
        PaymentMethod paymentMethod

){
}
