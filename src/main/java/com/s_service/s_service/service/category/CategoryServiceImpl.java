package com.s_service.s_service.service.category;

import com.s_service.s_service.dto.request.category.CategoryCreationRequest;
import com.s_service.s_service.dto.response.category.CategoryResponse;
import com.s_service.s_service.dto.response.category.GetCategoryResponse;
import com.s_service.s_service.exception.AppException;
import com.s_service.s_service.exception.ErrorCode;
import com.s_service.s_service.mapper.CategoryMapper;
import com.s_service.s_service.model.Category;
import com.s_service.s_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public GetCategoryResponse addCategory(CategoryCreationRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setBenefits(Arrays.asList(request.getBenefits()));
        category = categoryRepository.save(category);
        return categoryMapper.toGetCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getAllCategoriesWithServices() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .filter(category -> category.getCategoryStatus() != Category.CategoryStatus.DELETED)
                .map(category -> {
                    List<com.s_service.s_service.model.Service> filteredServices = category.getServices().stream()
                            .filter(service -> service.getServiceStatus() != com.s_service.s_service.model.Service.ServiceStatus.DELETED)
                            .collect(Collectors.toList());

                    category.setServices(filteredServices);

                    return categoryMapper.toCategoryResponse(category);
                })
                .collect(Collectors.toList());
    }


    @Override
    public CategoryResponse getCategoryWithServices(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.CATEGORY_NOT_FOUND)
        );
        if (category.getCategoryStatus() == Category.CategoryStatus.DELETED) {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        List<com.s_service.s_service.model.Service> filteredServices = category.getServices().stream()
                .filter(service -> service.getServiceStatus() != com.s_service.s_service.model.Service.ServiceStatus.DELETED)
                .collect(Collectors.toList());

        category.setServices(filteredServices);
        return categoryMapper.toCategoryResponse(category);
    }

    @Override
    public List<GetCategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toGetCategoryResponse
        ).toList();
    }

    @Override
    public GetCategoryResponse updateCategory(int id, CategoryCreationRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.CATEGORY_NOT_FOUND)
        );

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setBenefits(Arrays.asList(request.getBenefits()));

        category = categoryRepository.save(category);

        return categoryMapper.toGetCategoryResponse(category);
    }
}
