package com.s_service.s_service.dto.request.order;

import com.s_service.s_service.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationRequest {
    private String name;
    private String email;
    private int serviceId;
    private Long price;
    private int stageId;
    private String staffName;
    private Order.OrderStatus status;
}
