package com.s_service.s_service.dto.response.order;

import com.s_service.s_service.model.Order;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class OrderResponse {
    private String orderId;
    private String name;
    private String email;
    private String serviceName;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private Order.PaymentMethod paymentMethod;
    private Order.OrderStatus status;
}
