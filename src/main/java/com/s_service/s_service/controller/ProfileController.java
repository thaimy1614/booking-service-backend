package com.s_service.s_service.controller;

import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.request.profile.UpdateProfileRequest;
import com.s_service.s_service.dto.response.ProfileResponse;
import com.s_service.s_service.service.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.api.prefix}/user/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping()
    ApiResponse<ProfileResponse> getMyInfo(
            JwtAuthenticationToken token
    ) {
        String userId = token.getName();
        ProfileResponse response = profileService.getMyInfo(userId);
        return ApiResponse.<ProfileResponse>builder()
                .message("Get my info successfully")
                .result(response)
                .build();
    }

    @PostMapping()
    ApiResponse<ProfileResponse> updateProfile(
            UpdateProfileRequest request,
            JwtAuthenticationToken token
    ) {
        String userId = token.getName();
        ProfileResponse response = profileService.updateProfile(request, userId);
        return ApiResponse.<ProfileResponse>builder()
                .message("Update profile information successfully!")
                .result(response)
                .build();
    }

    @GetMapping("/get-all")
    ApiResponse<Page<ProfileResponse>> getAllProfile(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProfileResponse> response = profileService.getAll(pageable);
        return ApiResponse.<Page<ProfileResponse>>builder()
                .message("Get all profiles page:" + page + ", size:" + size + " successfully!")
                .result(response)
                .build();
    }
}
