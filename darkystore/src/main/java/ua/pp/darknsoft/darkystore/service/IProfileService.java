package ua.pp.darknsoft.darkystore.service;

import ua.pp.darknsoft.darkystore.dto.ProfileRequestDto;
import ua.pp.darknsoft.darkystore.dto.ProfileResponseDto;
import ua.pp.darknsoft.darkystore.model.Customer;

public interface IProfileService {
    ProfileResponseDto getProfile();

    ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto);

    Customer getAuthenticatedCustomer();
}
