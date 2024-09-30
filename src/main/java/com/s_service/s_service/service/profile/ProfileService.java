package com.s_service.s_service.service.profile;

import com.s_service.s_service.dto.request.profile.UpdateProfileRequest;
import com.s_service.s_service.dto.response.account.ProfileResponse;
import com.s_service.s_service.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileService {
    Profile saveProfile(Profile profile);

    ProfileResponse getMyInfo(String userId);

    ProfileResponse updateProfile(UpdateProfileRequest request, String userId);

    Page<ProfileResponse> getAll(Pageable pageable);
}
