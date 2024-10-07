package com.s_service.s_service.service.order;

import com.s_service.s_service.model.Order;
import com.s_service.s_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    @Override
    public Long countOrders() {
        return orderRepository.count();
    }

    @Override
    public Long getTotalRevenue() {
        List<Order> orders = orderRepository.findAllByStatus(Order.OrderStatus.DONE);
        AtomicLong totalRevenue = new AtomicLong(0L);
        orders.forEach(order -> {
            totalRevenue.addAndGet(order.getPrice());
        });
        return totalRevenue.get();
    }
}
