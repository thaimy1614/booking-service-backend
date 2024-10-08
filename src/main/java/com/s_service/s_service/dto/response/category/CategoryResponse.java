package com.s_service.s_service.dto.response.category;

import com.s_service.s_service.dto.response.service.ServiceResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryResponse {
    private int id;
    private String name;
    private String description;
    private List<ServiceResponse> services;
    private List<String> benefits;
}
