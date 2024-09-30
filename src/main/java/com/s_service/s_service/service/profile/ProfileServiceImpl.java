package com.s_service.s_service.service.profile;

import com.s_service.s_service.dto.request.profile.UpdateProfileRequest;
import com.s_service.s_service.dto.response.account.ProfileResponse;
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
        Profile profile = profileRepository.findById(userId).orElseGet(
                Profile::new
        );

        profile = profileMapper.toProfile(request);
        profileRepository.save(profile);

        return profileMapper.toProfileResponse(profile);
    }

    @Override
    public Page<ProfileResponse> getAll(Pageable pageable) {
        Page<Profile> profiles = profileRepository.findAll(pageable);

        return profiles.map(profileMapper::toProfileResponse);
    }


}
