package com.s_service.s_service.mapper;

import com.s_service.s_service.dto.request.account.SignupRequest;
import com.s_service.s_service.dto.request.profile.UpdateProfileRequest;
import com.s_service.s_service.dto.response.account.ProfileResponse;
import com.s_service.s_service.model.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toProfile(SignupRequest request);

    ProfileResponse toProfileResponse(Profile profile);

    Profile toProfile(UpdateProfileRequest request);
}
