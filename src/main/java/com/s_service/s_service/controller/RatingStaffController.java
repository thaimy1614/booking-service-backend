package com.s_service.s_service.controller;

import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.request.rating.staff.RatingStaffRequest;
import com.s_service.s_service.dto.response.profile.ProfileResponse;
import com.s_service.s_service.dto.response.rating.staff.RatingStaffResponse;
import com.s_service.s_service.model.Profile;
import com.s_service.s_service.service.profile.ProfileService;
import com.s_service.s_service.service.rating.staff.RatingStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.api.prefix}/rating-staff")
public class RatingStaffController {
    private final RatingStaffService ratingStaffService;
    private final ProfileService profileService;

    @PostMapping
    ApiResponse<RatingStaffResponse> addStaffRating(
            @RequestBody RatingStaffRequest ratingStaffRequest,
            JwtAuthenticationToken token
    ) {
        String userId = token.getName();
        ProfileResponse myProfile = profileService.getMyInfo(userId);
        RatingStaffResponse response = ratingStaffService.addStaffRating(myProfile.getEmail(), ratingStaffRequest);
        return ApiResponse.<RatingStaffResponse>builder()
                .message("add new rating successfully!")
                .result(response)
                .build();
    }
}
