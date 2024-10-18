package com.s_service.s_service.service.rating.staff;

import com.s_service.s_service.dto.request.rating.staff.RatingStaffRequest;
import com.s_service.s_service.dto.response.rating.staff.AverageRatingResponse;
import com.s_service.s_service.dto.response.rating.staff.RatingStaffResponse;
import com.s_service.s_service.mapper.RatingMapper;
import com.s_service.s_service.model.RatingStaff;
import com.s_service.s_service.repository.RatingStaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingStaffServiceImpl implements RatingStaffService {
    private final RatingStaffRepository ratingStaffRepository;
    private final RatingMapper ratingMapper;

    @Override
    public RatingStaffResponse addStaffRating(RatingStaffRequest ratingStaffRequest) {
        RatingStaff ratingStaff = ratingMapper.toRatingStaff(ratingStaffRequest);
        ratingStaffRepository.save(ratingStaff);
        return ratingMapper.toRatingStaffResponse(ratingStaff);
    }

    @Override
    public List<AverageRatingResponse> getAverageRating() {
        return ratingStaffRepository.findAverageRatingGroupedByStaffName();
    }
}
