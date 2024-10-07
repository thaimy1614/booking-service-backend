package com.s_service.s_service.controller;


import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.request.category.CategoryCreationRequest;
import com.s_service.s_service.dto.request.profile.UpdateProfileRequest;
import com.s_service.s_service.dto.request.service.ServiceRequest;
import com.s_service.s_service.dto.response.category.GetCategoryResponse;
import com.s_service.s_service.dto.response.profile.ProfileResponse;
import com.s_service.s_service.dto.response.service.ServiceResponse;
import com.s_service.s_service.service.category.CategoryService;
import com.s_service.s_service.service.profile.ProfileService;
import com.s_service.s_service.service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("${application.api.prefix}/admin")
public class AdminController {
    private final ProfileService profileService;
    private final CategoryService categoryService;
    private final ServiceService serviceService;

    @GetMapping("/user/count")
    ApiResponse<Long> getUserCount() {
        long count = profileService.countUsers();
        return ApiResponse.<Long>builder()
                .result(count)
                .message("Count number of users successfully")
                .build();
    }

    @GetMapping("/service/count")
    ApiResponse<Long> getServiceCount() {
        long count = serviceService.countServices();
        return ApiResponse.<Long>builder()
                .result(count)
                .message("Count number of services successfully")
                .build();
    }

    @GetMapping("/user")
    ApiResponse<Page<ProfileResponse>> getAllProfile(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProfileResponse> response = profileService.getAll(pageable);
        return ApiResponse.<Page<ProfileResponse>>builder()
                .message("Get all profiles page:" + page + ", size:" + size + " successfully!")
                .result(response)
                .build();
    }

    @PutMapping("/user/{userId}")
    ApiResponse<ProfileResponse> updateUserProfile(
            @PathVariable("userId") String userId,
            @RequestBody UpdateProfileRequest request
    ) {
        ProfileResponse response = profileService.updateProfile(request, userId);
        return ApiResponse.<ProfileResponse>builder()
                .message("Update profile information successfully!")
                .result(response)
                .build();
    }

    @GetMapping("/category")
    ApiResponse<List<GetCategoryResponse>> getAllCategory() {
        List<GetCategoryResponse> response = categoryService.getAllCategories();
        return ApiResponse.<List<GetCategoryResponse>>builder()
                .message("Get all categories successfully!")
                .result(response)
                .build();
    }

    @PostMapping("/category")
    ApiResponse<GetCategoryResponse> addCategory(
            @RequestBody CategoryCreationRequest request
    ) {
        GetCategoryResponse response = categoryService.addCategory(request);
        return ApiResponse.<GetCategoryResponse>builder()
                .message("Add category successfully!")
                .result(response)
                .build();
    }

    @PutMapping("/category/{id}")
    ApiResponse<GetCategoryResponse> updateCategory(
            @PathVariable int id,
            @RequestBody CategoryCreationRequest request
    ) {
        GetCategoryResponse response = categoryService.updateCategory(id, request);
        return ApiResponse.<GetCategoryResponse>builder()
                .message("Update category successfully!")
                .result(response)
                .build();
    }

    @GetMapping("/service")
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

    @PostMapping("/service")
    ApiResponse<ServiceResponse> addService(@RequestBody ServiceRequest request) {
        ServiceResponse response = serviceService.addService(request);
        return ApiResponse.<ServiceResponse>builder()
                .message("Add service successfully!")
                .result(response)
                .build();
    }

    @PutMapping("/service/{id}")
    ApiResponse<ServiceResponse> updateService(
            @PathVariable int id,
            @RequestBody ServiceRequest request
    ) {
        ServiceResponse response = serviceService.updateService(id, request);
        return ApiResponse.<ServiceResponse>builder()
                .message("Update service successfully!")
                .result(response)
                .build();
    }
}
