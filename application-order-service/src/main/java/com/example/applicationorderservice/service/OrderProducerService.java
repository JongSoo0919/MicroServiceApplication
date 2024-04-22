package com.example.applicationorderservice.service;

import com.example.applicationorderservice.dto.Field;
import com.example.applicationorderservice.dto.KafkaOrderDto;
import com.example.applicationorderservice.dto.Payload;
import com.example.applicationorderservice.dto.Schema;
import com.example.applicationorderservice.dto.request.OrderRequestDto;
import com.example.applicationorderservice.dto.response.OrderResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OrderResponseDto send(String topic, OrderResponseDto orderResponseDto) {
        List<Field> fields = Arrays.asList(
                new Field("string", true, "order_id")
                ,new Field("string", true, "user_id")
                ,new Field("string", true, "product_id")
                ,new Field("int32", true, "qty")
                ,new Field("int32", true, "unit_price")
                ,new Field("int32", true, "total_price")
        );

        Schema schema = Schema.builder()
                .type("struct")
                .fields(fields)
                .optional(false)
                .name("orders")
                .build();

        Payload payload = Payload.builder()
                .order_id(orderResponseDto.getOrderId())
                .user_id(orderResponseDto.getUserId())
                .product_id(orderResponseDto.getProductId())
                .qty(orderResponseDto.getQty())
                .unit_price(orderResponseDto.getUnitPrice())
                .total_price(orderResponseDto.getTotalPrice())
                .order_id(orderResponseDto.getOrderId())
                .build();

        KafkaOrderDto kafkaOrderDto = KafkaOrderDto.builder()
                .schema(schema)
                .payload(payload)
                .build();

        String jsonInString = "";
        try{
            jsonInString = objectMapper.writeValueAsString(kafkaOrderDto);
        } catch (JsonProcessingException e) {
            log.error("Error while converting OrderRequestDto", e);
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Order Producer sent data from the Order microservice {}", kafkaOrderDto);

        return orderResponseDto;
    }
}
