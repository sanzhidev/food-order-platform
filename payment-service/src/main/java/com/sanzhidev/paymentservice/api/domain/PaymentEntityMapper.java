package com.sanzhidev.paymentservice.api.domain;

import com.sanzhidev.api.http.CreatePaymentResponseDto;
import com.sanzhidev.api.http.order.CreatePaymentRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;


@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PaymentEntityMapper {
    PaymentEntity toEntity(CreatePaymentRequestDto request);


    @Mapping(source = "id",target = "paymentId")
    CreatePaymentResponseDto toResponseDto(PaymentEntity savedEntity);
}
