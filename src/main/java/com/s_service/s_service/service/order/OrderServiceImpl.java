package com.s_service.s_service.service.order;

import com.s_service.s_service.dto.request.order.OrderCreationRequest;
import com.s_service.s_service.dto.response.order.CategoryAnalysisResponse;
import com.s_service.s_service.dto.response.order.OrderResponse;
import com.s_service.s_service.exception.AppException;
import com.s_service.s_service.exception.ErrorCode;
import com.s_service.s_service.mapper.OrderMapper;
import com.s_service.s_service.model.Order;
import com.s_service.s_service.model.Profile;
import com.s_service.s_service.repository.OrderRepository;
import com.s_service.s_service.repository.ProfileRepository;
import com.s_service.s_service.repository.ServiceRepository;
import com.s_service.s_service.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProfileRepository profileRepository;
    private final ServiceRepository serviceRepository;
    private final StageRepository stageRepository;
    private final OrderMapper orderMapper;

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

    @Override
    public List<OrderResponse> getMyAllOrders(String userId) {
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.PROFILE_NOT_FOUND)
        );
        List<Order> orders = orderRepository.findAllByEmail(profile.getEmail());
        return orders.stream().map(order -> OrderResponse.builder()
                .id(order.getId())
                .name(order.getName())
                .email(order.getEmail())
                .serviceName(order.getService().getName())
                .createdDate(order.getCreatedDate())
                .updatedDate(order.getUpdatedDate())
                .price(order.getPrice())
                .status(order.getStatus())
                .stageName(order.getStage().getName())
                .stageId(order.getStage().getId())
                .serviceId(order.getService().getId())
                .staffName(order.getStaffName())
                .build()).toList();
    }

    @Override
    public OrderResponse getMyOrder(String userId, String orderId) {
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.PROFILE_NOT_FOUND)
        );
        Order order = orderRepository.findByIdAndEmail(orderId, profile.getEmail()).orElseThrow(
                () -> new AppException(ErrorCode.ORDER_NOT_FOUND)
        );
        return OrderResponse.builder()
                .id(order.getId())
                .name(order.getName())
                .email(order.getEmail())
                .serviceName(order.getService().getName())
                .createdDate(order.getCreatedDate())
                .updatedDate(order.getUpdatedDate())
                .price(order.getPrice())
                .status(order.getStatus())
                .stageName(order.getStage().getName())
                .stageId(order.getStage().getId())
                .serviceId(order.getService().getId())
                .staffName(order.getStaffName())
                .build();
    }

    @Override
    public OrderResponse createOrder(OrderCreationRequest request) {
        Order order = new Order();
        order.setEmail(request.getEmail());
        order.setName(request.getName());
        order.setService(serviceRepository.findById(request.getServiceId()).orElseThrow(
                () -> new AppException(ErrorCode.SERVICE_NOT_FOUND)
        ));
        order.setStage(stageRepository.findById(1).orElseThrow(
                () -> new AppException(ErrorCode.STAGE_NOT_FOUND)
        ));
        order.setPrice(request.getPrice());
        order.setStatus(Order.OrderStatus.PENDING);
        OrderResponse response = orderMapper.toOrderResponse(orderRepository.save(order));
        response.setServiceName(order.getService().getName());
        response.setStageName(order.getStage().getName());
        response.setStageId(order.getStage().getId());
        response.setServiceId(order.getService().getId());
        response.setStaffName(order.getStaffName());
        return response;
    }

    @Override
    public OrderResponse updateOrder(String orderId, OrderCreationRequest request) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new AppException(ErrorCode.ORDER_NOT_FOUND)
        );
        order.setName(request.getName());
        order.setEmail(request.getEmail());
        order.setService(serviceRepository.findById(request.getServiceId()).orElseThrow(
                () -> new AppException(ErrorCode.SERVICE_NOT_FOUND)
        ));
        order.setStage(stageRepository.findById(request.getStageId()).orElseThrow(
                () -> new AppException(ErrorCode.STAGE_NOT_FOUND)
        ));
        order.setPrice(request.getPrice());
        order.setStatus(request.getStageId()==4?Order.OrderStatus.DONE:Order.OrderStatus.PENDING);
        OrderResponse response = orderMapper.toOrderResponse(orderRepository.save(order));
        response.setServiceName(order.getService().getName());
        response.setStageName(order.getStage().getName());
        response.setStageId(order.getStage().getId());
        response.setServiceId(order.getService().getId());
        response.setStaffName(order.getStaffName());
        return response;
    }

    public void deleteOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new AppException(ErrorCode.ORDER_NOT_FOUND)
        );
        order.setStatus(Order.OrderStatus.DELETED);
        orderRepository.save(order);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> {
                    OrderResponse response = orderMapper.toOrderResponse(order);
                    response.setServiceName(order.getService().getName());
                    response.setStageName(order.getStage().getName());
                    response.setStageId(order.getStage().getId());
                    response.setServiceId(order.getService().getId());
                    response.setStaffName(order.getStaffName());
                    return response;
                })
                .toList();
    }


}
