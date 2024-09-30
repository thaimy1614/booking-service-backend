package com.s_service.s_service.controller;


import com.s_service.s_service.dto.ApiResponse;
import com.s_service.s_service.dto.request.category.CategoryCreationRequest;
import com.s_service.s_service.dto.request.profile.UpdateProfileRequest;
import com.s_service.s_service.dto.response.category.GetCategoryResponse;
import com.s_service.s_service.dto.response.profile.ProfileResponse;
import com.s_service.s_service.service.category.CategoryService;
import com.s_service.s_service.service.profile.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("${application.api.prefix}/admin")
public class AdminController {
    private final ProfileService profileService;
    private final CategoryService categoryService;

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

    @PutMapping("/user/{userId}")
    ApiResponse<ProfileResponse> updateUserProfile(
            @PathVariable("userId") String userId,
            @RequestBody UpdateProfileRequest request
    ) {
        ProfileResponse response = profileService.updateProfile(request, userId);
        return ApiResponse.<ProfileResponse>builder()
                .message("Update profile information successfully!")
                .result(response)
                .build();
    }

    @PostMapping("/category")
    ApiResponse<GetCategoryResponse> addCategory(
            @RequestBody CategoryCreationRequest request
    ) {
        GetCategoryResponse response = categoryService.addCategory(request);
        return ApiResponse.<GetCategoryResponse>builder()
                .message("Add category successfully!")
                .result(response)
                .build();
    }
}
