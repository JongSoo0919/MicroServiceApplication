package com.example.applicationcatalogueservice.service;

import com.example.applicationcatalogueservice.dto.response.CatalogResponseDto;

import java.util.List;

public interface CatalogService {
    List<CatalogResponseDto> getAllCatalogs();
}
