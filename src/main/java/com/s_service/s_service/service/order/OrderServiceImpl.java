package com.s_service.s_service.service.order;

import com.s_service.s_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    @Override
    public Long countOrders() {
        return orderRepository.count();
    }
}
