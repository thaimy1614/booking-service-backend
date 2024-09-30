package com.s_service.s_service.dto.response.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceResponse {
    private String name;
    private String categoryName;
    private int handleTime;
    private String description;
    private int price;
}
