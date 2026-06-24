package com.sanzhidev.orderservice.external;

import com.sanzhidev.api.http.CreatePaymentResponseDto;
import com.sanzhidev.api.http.order.CreatePaymentRequestDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(
        accept = "application/json",
        contentType = "application/json",
        url = "/api/payments"

)
public interface PaymentHttpClient {

    @PostExchange
    CreatePaymentResponseDto createPayment(@RequestBody CreatePaymentRequestDto request);
}
