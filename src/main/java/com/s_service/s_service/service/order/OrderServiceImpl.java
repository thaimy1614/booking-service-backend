package com.s_service.s_service.service.order;

import com.s_service.s_service.dto.response.order.CategoryAnalysisResponse;
import com.s_service.s_service.model.Category;
import com.s_service.s_service.model.Order;
import com.s_service.s_service.repository.CategoryRepository;
import com.s_service.s_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;

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

    @Override
    public List<CategoryAnalysisResponse> analyzeCategory() {
//        List<Category> categories = categoryRepository.findAll();
//        List<CategoryAnalysisResponse> responses = new ArrayList<>();
//        categories.forEach(category -> {
//            List<com.s_service.s_service.model.Service> services = category.getServices();
//            Long count = orderRepository.countAllByServiceInAndStatus(services, Order.OrderStatus.DONE);
//            responses.add(CategoryAnalysisResponse.builder()
//                            .label(category.getName())
//                            .total(count)
//                    .build());
//        });
//        return responses;


        List<Object[]> results = orderRepository.countOrdersByCategory(Order.OrderStatus.DONE);

        // Transform the result into the response
        return results.stream()
                .map(result -> CategoryAnalysisResponse.builder()
                        .label((String) result[0])  // category name
                        .value((Long) result[1])    // order count
                        .build())
                .collect(Collectors.toList());
    }



}
