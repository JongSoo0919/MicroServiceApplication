package com.user.applicationuserservice.client;

import com.user.applicationuserservice.dto.response.OrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "application-order-service")
public interface OrderServiceClient {
    @GetMapping("/order-service/{userId}/orders_ng")
    List<OrderResponseDto> getOrders(@PathVariable String userId);
}
