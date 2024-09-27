package com.s_service.s_service.mapper;

import com.s_service.s_service.dto.request.SignupRequest;
import com.s_service.s_service.dto.response.ProfileResponse;
import com.s_service.s_service.model.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toProfile(SignupRequest request);

    ProfileResponse toProfileResponse(Profile profile);
}
