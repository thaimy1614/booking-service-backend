package com.s_service.s_service.controller;

import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.response.category.CategoryResponse;
import com.s_service.s_service.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${application.api.prefix}/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    ApiResponse<List<CategoryResponse>> getAll() {
        List<CategoryResponse> response = categoryService.getAllCategoriesWithServices();

        return ApiResponse.<List<CategoryResponse>>builder()
                .message("Get all categories with services successfully!")
                .result(response)
                .build();
    }
}
