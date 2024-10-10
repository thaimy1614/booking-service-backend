package com.s_service.s_service.dto.response.category;

import com.s_service.s_service.model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetCategoryResponse {
    private int id;
    private String name;
    private String description;
    private Category.CategoryStatus categoryStatus;
    private List<String> benefits;
}
