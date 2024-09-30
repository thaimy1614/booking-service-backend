package com.s_service.s_service.service.service;

import com.s_service.s_service.dto.response.service.ServiceResponse;
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
        return services.map(serviceMapper::toServiceResponse);
    }
}
