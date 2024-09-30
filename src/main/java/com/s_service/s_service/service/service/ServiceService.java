package com.s_service.s_service.service.service;

import com.s_service.s_service.dto.response.service.ServiceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceService {
    Page<ServiceResponse> getAll(Pageable pageable);
    ServiceResponse getServiceById(int id);
}