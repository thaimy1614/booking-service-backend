package com.s_service.s_service.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationRequest {
    private String userId;
    private int serviceId;
}
