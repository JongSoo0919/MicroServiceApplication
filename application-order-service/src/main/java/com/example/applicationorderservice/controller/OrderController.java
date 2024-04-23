package com.example.applicationorderservice.controller;

import com.example.applicationorderservice.dto.request.OrderRequestDto;
import com.example.applicationorderservice.dto.response.OrderResponseDto;
import com.example.applicationorderservice.service.KafkaProducerService;
import com.example.applicationorderservice.service.OrderProducerService;
import com.example.applicationorderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final KafkaProducerService kafkaProducerService;
    private final OrderProducerService orderProducerService;

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in Order Service";
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody OrderRequestDto orderRequestDto
            ,@PathVariable("userId") String userId) {
        orderRequestDto.setUserId(userId);
        orderRequestDto.setTotalPrice(orderRequestDto.getUnitPrice() * orderRequestDto.getQty());

        /* send this order to the kafka */
//        kafkaProducerService.send("example-catalog-topic", orderRequestDto);
//
//        /* kafka data */
        log.info("Before call add order data");
        OrderResponseDto orderResponseDto = orderService.createOrder(orderRequestDto);
//        orderProducerService.send("orders", orderResponseDto);
        log.info("After call add order data");

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByUserId(
            @PathVariable("userId") String userId
    ) {
        log.info("Before call retrieve order data");
        log.info("After call retrieve order data");
        return ResponseEntity.ok().body(orderService.getOrdersByUserId(userId));

    }
}
