package com.s_service.s_service.controller;

import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.response.ProfileResponse;
import com.s_service.s_service.service.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.api.prefix}/profile")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping()
    ApiResponse<ProfileResponse> getMyInfo(
            JwtAuthenticationToken token
    ){
        String userId = token.getName();
        ProfileResponse response = profileService.getMyInfo(userId);
        return ApiResponse.<ProfileResponse>builder()
                .message("Get my info successfully")
                .result(response)
                .build();
    }
}
