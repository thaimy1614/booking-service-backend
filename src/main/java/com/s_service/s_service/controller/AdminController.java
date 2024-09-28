package com.s_service.s_service.controller;


import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.response.ProfileResponse;
import com.s_service.s_service.service.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("${application.api.prefix}/admin")
public class AdminController {
    private final ProfileService profileService;

    @GetMapping("/user")
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
