package com.s_service.s_service.controller;

import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.response.service.ServiceResponse;
import com.s_service.s_service.service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
