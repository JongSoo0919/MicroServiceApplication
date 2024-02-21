package com.example.applicationorderservice.service;

import com.example.applicationorderservice.dto.request.OrderRequestDto;
import com.example.applicationorderservice.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
    List<OrderResponseDto> getOrdersByUserId(String userId);
    OrderResponseDto getOrderByOrderId(String orderId);
}
