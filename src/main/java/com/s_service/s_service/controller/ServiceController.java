package com.s_service.s_service.controller;

import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.response.service.ServiceResponse;
import com.s_service.s_service.service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${application.api.prefix}/service")
public class ServiceController {
    private final ServiceService serviceService;

    @GetMapping
    ApiResponse<Page<ServiceResponse>> getServices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ServiceResponse> response = serviceService.getAll(pageable);
        return ApiResponse.<Page<ServiceResponse>>builder()
                .message("Get services successfully!")
                .result(response)
                .build();
    }
}
