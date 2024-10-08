package com.s_service.s_service.service.order;

import com.s_service.s_service.dto.response.order.CategoryAnalysisResponse;

import java.util.List;

public interface OrderService {
    Long countOrders();

    Long getTotalRevenue();

    List<CategoryAnalysisResponse> analyzeCategory();
}
