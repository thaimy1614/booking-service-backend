package com.s_service.s_service.mapper;

import com.s_service.s_service.dto.response.order.OrderResponse;
import com.s_service.s_service.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);
}
