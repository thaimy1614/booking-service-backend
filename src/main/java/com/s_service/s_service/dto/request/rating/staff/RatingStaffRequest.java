package com.s_service.s_service.dto.request.rating.staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingStaffRequest {
    private String staffName;
    private double rating;
    private String feedback;
}
