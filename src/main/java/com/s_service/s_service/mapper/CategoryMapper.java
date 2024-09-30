package com.s_service.s_service.mapper;

import com.s_service.s_service.dto.request.category.CategoryCreationRequest;
import com.s_service.s_service.dto.response.category.CategoryResponse;
import com.s_service.s_service.dto.response.category.GetCategoryResponse;
import com.s_service.s_service.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreationRequest request);

    GetCategoryResponse toGetCategoryResponse(Category category);

    CategoryResponse toCategoryResponse(Category category);
}
