package com.s_service.s_service.dto.response.rating.staff;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingStaffResponse {
    private int id;
    private String staffName;
    private double rating;
    private String feedback;
}
