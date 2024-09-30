package com.s_service.s_service.dto.response.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCategoryResponse {
    private String name;
    private String description;
}
