package com.s_service.s_service.dto.response.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryAnalysisResponse {
    private String label;
    private Long value;
}
