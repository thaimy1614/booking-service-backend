package com.s_service.s_service.dto.request.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreationRequest {
    private String name;
    private String description;
    private List<String> benefits;
}
