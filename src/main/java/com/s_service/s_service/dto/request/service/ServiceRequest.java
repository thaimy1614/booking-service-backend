package com.s_service.s_service.dto.request.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {
    private String name;
    private String description;
    private int categoryId;
    private int handleTime;
    private int price;
}
