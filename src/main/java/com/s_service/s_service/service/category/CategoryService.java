package com.s_service.s_service.service.category;

import com.s_service.s_service.dto.request.category.CategoryCreationRequest;
import com.s_service.s_service.dto.response.category.CategoryResponse;
import com.s_service.s_service.dto.response.category.GetCategoryResponse;

import java.util.List;

public interface CategoryService {
    GetCategoryResponse addCategory(CategoryCreationRequest request);

    List<CategoryResponse> getAllCategoriesWithServices();
}
