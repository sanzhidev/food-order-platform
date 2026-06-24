package com.sanzhidev.api.http;



import com.sanzhidev.api.http.payment.PaymentMethod;
import com.sanzhidev.api.http.payment.PaymentStatus;

import java.math.BigDecimal;

public record CreatePaymentResponseDto (
        Long paymentId,
        PaymentStatus paymentStatus,
        Long orderId,
        PaymentMethod paymentMethod,
        BigDecimal amount

){

}
