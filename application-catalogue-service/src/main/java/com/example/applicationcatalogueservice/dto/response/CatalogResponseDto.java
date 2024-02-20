package com.example.applicationcatalogueservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatalogResponseDto {
    private String productId;
    private String productName;
    private Integer unitPrice;
    private Integer stock;
    private LocalDate createdAt;

    @Builder
    public CatalogResponseDto(String productId, String productName, Integer unitPrice, Integer stock, LocalDate createdAt) {
        this.productId = productId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.createdAt = createdAt;
    }
}
