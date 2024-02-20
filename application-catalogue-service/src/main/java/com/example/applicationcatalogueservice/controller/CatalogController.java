package com.example.applicationcatalogueservice.controller;

import com.example.applicationcatalogueservice.dto.response.CatalogResponseDto;
import com.example.applicationcatalogueservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogue-service")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;
    @GetMapping("/health_check")
    public String status() {
        return "It's Working in Catalog Service";
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<CatalogResponseDto>> getAllCatalogs() {
        return ResponseEntity.ok().body(catalogService.getAllCatalogs());
    }

}
