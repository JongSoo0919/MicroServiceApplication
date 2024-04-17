package com.example.applicationorderservice.service;

import com.example.applicationorderservice.dto.request.OrderRequestDto;
import com.example.applicationorderservice.dto.response.OrderResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderRequestDto send(String topic, OrderRequestDto orderRequestDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString = "";
        try{
            jsonInString = objectMapper.writeValueAsString(orderRequestDto);
        } catch (JsonProcessingException e) {
            log.error("Error while converting OrderRequestDto", e);
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer sent data from the Order microservice {}", jsonInString);

        return orderRequestDto;
    }
}
