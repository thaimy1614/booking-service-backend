package com.s_service.s_service.service.profile;

import com.s_service.s_service.dto.request.profile.UpdateProfileRequest;
import com.s_service.s_service.dto.response.profile.ProfileResponse;
import com.s_service.s_service.exception.AppException;
import com.s_service.s_service.exception.ErrorCode;
import com.s_service.s_service.mapper.ProfileMapper;
import com.s_service.s_service.model.Profile;
import com.s_service.s_service.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public Profile saveProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public ProfileResponse getMyInfo(String userId) {
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("Profile not found"));
        return profileMapper.toProfileResponse(profile);
    }

    @Override
    public ProfileResponse updateProfile(UpdateProfileRequest request, String userId) {
        Profile profile = profileRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.PROFILE_NOT_FOUND)
        );

        profile.setName(request.getName());
        profile.setAddress(request.getAddress());
        profile.setPhone(request.getPhone());

        profileRepository.save(profile);

        return profileMapper.toProfileResponse(profile);
    }

    @Override
    public Page<ProfileResponse> getAll(Pageable pageable) {
        Page<Profile> profiles = profileRepository.findAll(pageable);

        return profiles.map(profileMapper::toProfileResponse);
    }

    @Override
    public Long countUsers() {
        return profileRepository.count();
    }


}
