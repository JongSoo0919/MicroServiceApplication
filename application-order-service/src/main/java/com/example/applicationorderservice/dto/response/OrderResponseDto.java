package com.example.applicationorderservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDto {
    private String productId;
    private String userId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private LocalDate createdAt;

    private String orderId;

    @Builder
    public OrderResponseDto(String productId, String userId, Integer qty, Integer unitPrice, Integer totalPrice, LocalDate createdAt, String orderId) {
        this.productId = productId;
        this.userId = userId;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.createdAt = createdAt;
        this.orderId = orderId;
    }
}
