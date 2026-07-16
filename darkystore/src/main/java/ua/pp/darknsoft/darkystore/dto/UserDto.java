package ua.pp.darknsoft.darkystore.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class UserDto {
    private Long userId;
    private String name;
    private String email;
    private String mobileNumber;
    private Set<String> roles;
    private AddressDto address;
}
