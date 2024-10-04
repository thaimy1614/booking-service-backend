package com.s_service.s_service.dto.response.service;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ServiceResponse {
    private int id;
    private String name;
    private String categoryName;
    private int handleTime;
    private String description;
    private int price;
    private List<String> benefits;
}
