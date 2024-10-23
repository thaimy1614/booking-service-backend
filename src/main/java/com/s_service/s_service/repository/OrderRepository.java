package com.s_service.s_service.repository;

import com.s_service.s_service.model.Order;
import com.s_service.s_service.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByStatusIsNot(Order.OrderStatus status);

    Optional<Order> findByIdAndEmail(String id, String email);

    List<Order> findAllByEmail(String email);

    Long countAllByStatusIsNot(Order.OrderStatus status);

    @Query("SELECT c.name, COUNT(o) " +
            "FROM Order o " +
            "JOIN o.service s " +
            "JOIN s.category c " +
            "WHERE o.status != :status " +
            "GROUP BY c.name")
    List<Object[]> countOrdersByCategory(@Param("status") Order.OrderStatus status);
}
