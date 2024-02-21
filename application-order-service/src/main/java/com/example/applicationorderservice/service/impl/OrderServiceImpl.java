package com.example.applicationorderservice.service.impl;

import com.example.applicationorderservice.dto.request.OrderRequestDto;
import com.example.applicationorderservice.dto.response.OrderResponseDto;
import com.example.applicationorderservice.entity.OrderEntity;
import com.example.applicationorderservice.repository.OrderRepository;
import com.example.applicationorderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        OrderEntity entity = orderRepository.save(OrderEntity.builder()
                .productId(orderRequestDto.getProductId())
                .userId(orderRequestDto.getUserId())
                .qty(orderRequestDto.getQty())
                .unitPrice(orderRequestDto.getUnitPrice())
                .totalPrice(orderRequestDto.getUnitPrice() * orderRequestDto.getQty())
                .orderId(UUID.randomUUID().toString())
                .build());

        return OrderResponseDto.builder()
                .productId(entity.getProductId())
                .totalPrice(entity.getTotalPrice())
                .unitPrice(entity.getUnitPrice())
                .qty(entity.getQty())
                .createdAt(entity.getCreatedAt())
                .orderId(entity.getOrderId())
                .build();
    }

    @Override
    public List<OrderResponseDto> getOrdersByUserId(String userId) {
        Iterable<OrderEntity> entities = orderRepository.findByUserId(userId);
        List<OrderResponseDto> orderResponseDtos = new ArrayList<>();
        entities.forEach(entity -> {
            orderResponseDtos.add(OrderResponseDto.builder()
                    .productId(entity.getProductId())
                    .orderId(entity.getOrderId())
                    .unitPrice(entity.getUnitPrice())
                    .totalPrice(entity.getTotalPrice())
                    .qty(entity.getQty())
                    .createdAt(entity.getCreatedAt())
                    .build());
        });

        return orderResponseDtos;
    }

    @Override
    public OrderResponseDto getOrderByOrderId(String orderId) {
        OrderEntity entity = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("해당 주문이 존재하지 않습니다."));

        return OrderResponseDto.builder()
                .productId(entity.getProductId())
                .orderId(entity.getOrderId())
                .unitPrice(entity.getUnitPrice())
                .totalPrice(entity.getTotalPrice())
                .qty(entity.getQty())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
