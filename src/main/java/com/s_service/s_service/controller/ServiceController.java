package com.s_service.s_service.controller;

import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.response.service.ServiceResponse;
import com.s_service.s_service.service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${application.api.prefix}/service")
public class ServiceController {
    private final ServiceService serviceService;

    @GetMapping("/{id}")
    ApiResponse<ServiceResponse> getService(@PathVariable int id) {
        ServiceResponse response = serviceService.getServiceById(id);
        return ApiResponse.<ServiceResponse>builder()
                .message("Get services successfully!")
                .result(response)
                .build();
    }
}
