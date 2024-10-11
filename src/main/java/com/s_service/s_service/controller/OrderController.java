package com.s_service.s_service.controller;

import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.response.order.OrderResponse;
import com.s_service.s_service.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.api.prefix}/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping()
    ApiResponse<List<OrderResponse>> getMyOrders(JwtAuthenticationToken token){
        List<OrderResponse> response = orderService.getMyAllOrders(token.getName());
        return ApiResponse.<List<OrderResponse>>builder()
                .result(response)
                .message("Get all orders successfully!")
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<OrderResponse> getMyOrderDetail(
            @PathVariable String id,
            JwtAuthenticationToken token
    ){
        OrderResponse response = orderService.getMyOrder(token.getName(), id);
        return ApiResponse.<OrderResponse>builder()
                .result(response)
                .message("Get all orders successfully!")
                .build();
    }

}
