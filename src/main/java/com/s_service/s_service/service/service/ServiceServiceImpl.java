package com.s_service.s_service.service.service;

import com.s_service.s_service.dto.response.service.ServiceResponse;
import com.s_service.s_service.exception.AppException;
import com.s_service.s_service.exception.ErrorCode;
import com.s_service.s_service.mapper.ServiceMapper;
import com.s_service.s_service.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
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
}
