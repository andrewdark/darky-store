package ua.pp.darknsoft.darkystore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProfileResponseDto {
    private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;
    private Set<String> roles;
    private AddressDto addressDto;
    private boolean emailUpdated;

}
