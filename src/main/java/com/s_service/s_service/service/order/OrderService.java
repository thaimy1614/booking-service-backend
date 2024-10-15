package com.s_service.s_service.service.order;

import com.s_service.s_service.dto.request.order.OrderCreationRequest;
import com.s_service.s_service.dto.response.order.CategoryAnalysisResponse;
import com.s_service.s_service.dto.response.order.OrderResponse;

import java.util.List;

public interface OrderService {
    Long countOrders();

    Long getTotalRevenue();

    List<CategoryAnalysisResponse> analyzeCategory();

    List<OrderResponse> getMyAllOrders(String userId);

    OrderResponse getMyOrder(String userId, String orderId);

    OrderResponse createOrder(OrderCreationRequest request);

    OrderResponse updateOrder(String orderId, OrderCreationRequest request);

    void deleteOrder(String orderId);

    List<OrderResponse> getAllOrders();
}
