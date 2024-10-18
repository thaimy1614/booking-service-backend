package com.s_service.s_service.dto.response.rating.staff;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AverageRatingResponse {
    private String staffName;
    private double rating;
}
