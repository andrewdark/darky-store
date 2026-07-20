package ua.pp.darknsoft.darkystore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.darkystore.dto.AddressDto;
import ua.pp.darknsoft.darkystore.dto.ProfileRequestDto;
import ua.pp.darknsoft.darkystore.dto.ProfileResponseDto;
import ua.pp.darknsoft.darkystore.model.Address;
import ua.pp.darknsoft.darkystore.model.Customer;
import ua.pp.darknsoft.darkystore.repository.CustomerRepository;
import ua.pp.darknsoft.darkystore.service.IProfileService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileServiceImpl implements IProfileService {
    private final CustomerRepository customerRepository;

    @Override
    public ProfileResponseDto getProfile() {
        Customer customer = getAuthenticatedCustomer();
        return mapCustomerToProfileResponseDto(customer);
    }

    @Override
    @Transactional
    public ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto) {
        Customer customer = getAuthenticatedCustomer();
        boolean isEmailUpdated = !customer.getEmail().equals(profileRequestDto.email().trim());
        BeanUtils.copyProperties(profileRequestDto, customer);
        Address address = customer.getAddress();
        if (address == null) {
            address = new Address();
            address.setCustomer(customer);
        }
        address.setStreet(profileRequestDto.street());
        address.setCity(profileRequestDto.city());
        address.setState(profileRequestDto.state());
        address.setPostalCode(profileRequestDto.postalCode());
        address.setCountry(profileRequestDto.country());
        customer.setAddress(address);
        customer = customerRepository.save(customer);
        ProfileResponseDto profileResponseDto = mapCustomerToProfileResponseDto(customer);
        profileResponseDto.setEmailUpdated(isEmailUpdated);
        return profileResponseDto;
    }

    public Customer getAuthenticatedCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        String email = authentication.getName();
        return customerRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private ProfileResponseDto mapCustomerToProfileResponseDto(Customer customer) {
        ProfileResponseDto profileResponseDto = new ProfileResponseDto();
        BeanUtils.copyProperties(customer, profileResponseDto);
        if (customer.getAddress() != null) {
            AddressDto addressDto = new AddressDto(
                    customer.getAddress().getStreet(),
                    customer.getAddress().getCity(),
                    customer.getAddress().getState(),
                    customer.getAddress().getPostalCode(),
                    customer.getAddress().getCountry()
            );
            profileResponseDto.setAddressDto(addressDto);
        }
        return profileResponseDto;
    }
}
