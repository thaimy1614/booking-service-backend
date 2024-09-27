package com.s_service.s_service.service.profile;

import com.s_service.s_service.dto.response.ProfileResponse;
import com.s_service.s_service.model.Profile;

public interface ProfileService {
    public Profile saveProfile(Profile profile);

    ProfileResponse getMyInfo(String userId);
}
