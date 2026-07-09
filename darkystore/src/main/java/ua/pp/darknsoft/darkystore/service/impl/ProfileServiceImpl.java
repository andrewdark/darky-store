package ua.pp.darknsoft.darkystore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.darkystore.dto.ProfileRequestDto;
import ua.pp.darknsoft.darkystore.dto.ProfileResponseDto;
import ua.pp.darknsoft.darkystore.repository.CustomerRepository;
import ua.pp.darknsoft.darkystore.service.IProfileService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileServiceImpl implements IProfileService {
    private final CustomerRepository customerRepository;

    @Override
    public ProfileResponseDto getProfile() {
        return new ProfileResponseDto("DUMMY DEGAN");
    }

    @Override
    public ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto) {
        return new ProfileResponseDto("DUMMY DEGAN");
    }
}
