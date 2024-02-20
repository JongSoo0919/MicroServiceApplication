package com.example.applicationcatalogueservice.service.impl;

import com.example.applicationcatalogueservice.dto.response.CatalogResponseDto;
import com.example.applicationcatalogueservice.entity.CatalogEntity;
import com.example.applicationcatalogueservice.repository.CatalogRepository;
import com.example.applicationcatalogueservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;


    @Override
    public List<CatalogResponseDto> getAllCatalogs() {
        List<CatalogResponseDto> catalogResponseDtos = new ArrayList<>();
        Iterable<CatalogEntity> catalogEntities = catalogRepository.findAll();
        catalogEntities.forEach(catalog -> {
            catalogResponseDtos.add(CatalogResponseDto.builder()
                    .productId(catalog.getProductId())
                    .productName(catalog.getProductName())
                    .stock(catalog.getStock())
                    .unitPrice(catalog.getUnitPrice())
                    .createdAt(catalog.getCreatedAt())
                    .build());
        });
        return catalogResponseDtos;
    }
}
