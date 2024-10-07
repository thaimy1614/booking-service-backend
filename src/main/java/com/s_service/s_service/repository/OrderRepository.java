package com.s_service.s_service.repository;

import com.s_service.s_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByStatus(Order.OrderStatus status);
}
