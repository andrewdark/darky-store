package ua.pp.darknsoft.darkystore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponseDto {
    private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;
    private AddressDto addressDto;
    private boolean emailUpdated;

}
