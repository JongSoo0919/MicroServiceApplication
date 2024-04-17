package com.example.applicationorderservice.controller;

import com.example.applicationorderservice.dto.request.OrderRequestDto;
import com.example.applicationorderservice.dto.response.OrderResponseDto;
import com.example.applicationorderservice.service.KafkaProducerService;
import com.example.applicationorderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {
    private final OrderService orderService;
    private final KafkaProducerService kafkaProducerService;

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in Order Service";
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody OrderRequestDto orderRequestDto
            ,@PathVariable("userId") String userId) {
        orderRequestDto.setUserId(userId);

        /* send this order to the kafka */
        kafkaProducerService.send("example-catalog-topic", orderRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderRequestDto));
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUserId(
            @PathVariable("userId") String userId
    ) {
        return ResponseEntity.ok().body(orderService.getOrdersByUserId(userId));
    }
}
