package com.example.applicationcatalogueservice.service;

import com.example.applicationcatalogueservice.entity.CatalogEntity;
import com.example.applicationcatalogueservice.repository.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final CatalogRepository catalogRepository;

    @KafkaListener(topics = "example-catalog-topic")
    public void updateQty(String kafkaMessage) {
        log.info("Kafka Message received: {}", kafkaMessage);
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            log.error("Error parsing JSON", e);
        }

        CatalogEntity catalogEntity = catalogRepository.findByProductId((String) map.get("productId"))
                .orElseThrow(() -> new RuntimeException("Catalog not found"));

        catalogEntity.updateQty((Integer)map.get("qty"));
        catalogRepository.save(catalogEntity);
    }
}
