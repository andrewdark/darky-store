package ua.pp.darknsoft.darkystore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProfileRequestDto(@NotBlank(message = "Name is required")
                                @Size(min = 5, max = 30, message = "The length of the name should be between 5 and 100 characters")
                                String name,

                                @NotBlank(message = "Email is required")
                                @Email(message = "Email address must be a valid value")
                                String email,

                                @NotBlank(message = "Mobile Number cannot be empty")
                                @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
                                String mobileNumber,

                                @NotBlank(message = "Street is required")
                                @Size(min = 5, max = 50, message = "The length of the street should be between 5 and 50 characters")
                                String street,

                                @NotBlank(message = "City is required")
                                @Size(min = 3, max = 30, message = "The length of the city should be between 3 and 30 characters")
                                String city,

                                @NotBlank(message = "State is required")
                                @Size(min = 2, max = 30, message = "The length of the state should be between 2 and 30 characters")
                                String state,

                                @NotBlank(message = "Postal Code is required")
                                @Pattern(regexp = "^\\d{5}$", message = "Postal code must be exactly 5 digits")
                                String postalCode,

                                @NotBlank(message = "Country is required")
                                @Size(min = 2, max = 2, message = "The length of the country should be 2 characters")
                                String country) {
}
