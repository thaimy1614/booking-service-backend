package com.s_service.s_service.mapper;

import com.s_service.s_service.dto.request.rating.staff.RatingStaffRequest;
import com.s_service.s_service.dto.response.rating.staff.RatingStaffResponse;
import com.s_service.s_service.model.RatingStaff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    RatingStaffResponse toRatingStaffResponse (RatingStaff ratingStaff);
    RatingStaff toRatingStaff (RatingStaffRequest ratingStaffRequest);
}
