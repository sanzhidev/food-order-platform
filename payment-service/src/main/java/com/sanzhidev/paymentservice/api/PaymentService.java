package com.sanzhidev.paymentservice.api;


import com.sanzhidev.api.http.order.CreatePaymentRequestDto;
import com.sanzhidev.api.http.CreatePaymentResponseDto;
import com.sanzhidev.paymentservice.api.domain.PaymentEntityMapper;
import com.sanzhidev.paymentservice.api.domain.PaymentEntityRepository;
import com.sanzhidev.api.http.payment.PaymentMethod;
import com.sanzhidev.api.http.payment.PaymentStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentEntityRepository repository;
    private final PaymentEntityMapper mapper;

    public CreatePaymentResponseDto makePayment(CreatePaymentRequestDto request) {
        var found = repository.findByOrderId(request.orderId());
        if (found.isPresent()) {
            log.info("Payment already exists for orderId={}", request.orderId());
            return mapper.toResponseDto(found.get());
        }

        var entity = mapper.toEntity(request);

        var status = request.paymentMethod().equals(PaymentMethod.QR)
                ? PaymentStatus.PAYMENT_FAILED
                : PaymentStatus.PAYMENT_SUCCEEDED;

        entity.setPaymentStatus(status);

        var savedEntity = repository.save(entity);
        return mapper.toResponseDto(savedEntity);
    }
}

