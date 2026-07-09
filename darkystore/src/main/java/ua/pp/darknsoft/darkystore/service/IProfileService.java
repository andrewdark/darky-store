package ua.pp.darknsoft.darkystore.service;

import ua.pp.darknsoft.darkystore.dto.ProfileRequestDto;
import ua.pp.darknsoft.darkystore.dto.ProfileResponseDto;

public interface IProfileService {
    ProfileResponseDto getProfile();

    ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto);
}
