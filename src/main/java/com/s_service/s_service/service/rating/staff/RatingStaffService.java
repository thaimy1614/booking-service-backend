package com.s_service.s_service.service.rating.staff;

import com.s_service.s_service.dto.request.rating.staff.RatingStaffRequest;
import com.s_service.s_service.dto.response.rating.staff.AverageRatingResponse;
import com.s_service.s_service.dto.response.rating.staff.RatingStaffResponse;

import java.util.List;

public interface RatingStaffService {
    RatingStaffResponse addStaffRating(RatingStaffRequest ratingStaffRequest);

    List<AverageRatingResponse> getAverageRating();
}
