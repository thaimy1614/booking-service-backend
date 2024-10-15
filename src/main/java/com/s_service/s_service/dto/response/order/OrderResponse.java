package com.s_service.s_service.dto.response.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.s_service.s_service.model.Order;
import com.s_service.s_service.model.Stage;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class OrderResponse {
    private String id;
    private String name;
    private String email;
    private String serviceName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate updatedDate;
    private Order.OrderStatus status;
    private String stageName;
    private Long price;
    private int stageId;
    private int serviceId;
}
