package com.s_service.s_service.repository;

import com.s_service.s_service.model.Order;
import com.s_service.s_service.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByStatus(Order.OrderStatus status);

    Long countAllByServiceInAndStatus(List<Service> service, Order.OrderStatus status);

    @Query("SELECT c.name, COUNT(o) " +
            "FROM Order o " +
            "JOIN o.service s " +
            "JOIN s.category c " +
            "WHERE o.status = :status " +
            "GROUP BY c.name")
    List<Object[]> countOrdersByCategory(@Param("status") Order.OrderStatus status);
}
