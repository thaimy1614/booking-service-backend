package com.s_service.s_service.service.service;

import com.s_service.s_service.dto.request.service.ServiceRequest;
import com.s_service.s_service.dto.response.service.ServiceResponse;
import com.s_service.s_service.exception.AppException;
import com.s_service.s_service.exception.ErrorCode;
import com.s_service.s_service.mapper.ServiceMapper;
import com.s_service.s_service.model.Category;
import com.s_service.s_service.repository.CategoryRepository;
import com.s_service.s_service.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final CategoryRepository categoryRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public Page<ServiceResponse> getAll(Pageable pageable) {
        Page<com.s_service.s_service.model.Service> services = serviceRepository.findAll(pageable);
        return services.map(service -> {
            ServiceResponse response = serviceMapper.toServiceResponse(service);
            response.setCategoryName(service.getCategory().getName());
            return response;
        });
    }

    @Override
    public ServiceResponse getServiceById(int id) {
        com.s_service.s_service.model.Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SERVICE_NOT_FOUND));
        return serviceMapper.toServiceResponse(service);
    }

    @Override
    public ServiceResponse addService(ServiceRequest serviceRequest) {
        Category category = categoryRepository.findById(serviceRequest.getCategoryId()).orElseThrow(
                () -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        com.s_service.s_service.model.Service service = serviceMapper.toService(serviceRequest);
        service.setCategory(category);
        return serviceMapper.toServiceResponse(serviceRepository.save(service));
    }

    @Override
    public ServiceResponse updateService(int id, ServiceRequest serviceRequest) {
        serviceRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.SERVICE_NOT_FOUND)
        );
        Category category = categoryRepository.findById(serviceRequest.getCategoryId()).orElseThrow(
                () -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        com.s_service.s_service.model.Service service = serviceMapper.toService(serviceRequest);
        service.setId(id);
        service.setCategory(category);
        return serviceMapper.toServiceResponse(serviceRepository.save(service));
    }

    @Override
    public Long countServices() {
        return serviceRepository.count();
    }
}
