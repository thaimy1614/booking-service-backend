package com.s_service.s_service.service.category;

import com.s_service.s_service.dto.request.category.CategoryCreationRequest;
import com.s_service.s_service.dto.response.category.CategoryResponse;
import com.s_service.s_service.dto.response.category.GetCategoryResponse;
import com.s_service.s_service.mapper.CategoryMapper;
import com.s_service.s_service.model.Category;
import com.s_service.s_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public GetCategoryResponse addCategory(CategoryCreationRequest request) {
        Category category = categoryMapper.toCategory(request);
        category = categoryRepository.save(category);
        return categoryMapper.toGetCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCategoriesWithServices() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toCategoryResponse
        ).toList();
    }
}
