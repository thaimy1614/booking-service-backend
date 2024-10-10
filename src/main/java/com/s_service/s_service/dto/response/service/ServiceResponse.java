package com.s_service.s_service.dto.response.service;

import com.s_service.s_service.model.Category;
import com.s_service.s_service.model.Service;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ServiceResponse {
    private int id;
    private String name;
    private String categoryName;
    private String description;
    private Service.ServiceStatus serviceStatus;
    private List<String> benefits;
}
