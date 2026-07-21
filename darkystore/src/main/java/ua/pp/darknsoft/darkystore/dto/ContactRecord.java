package ua.pp.darknsoft.darkystore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ua.pp.darknsoft.darkystore.model.Contact;

import java.time.Instant;

public record ContactRecord(Long contactId,
                            @Size(min = 4, max = 30, message = "Must be between 5 and 30 characters") String name,
                            @NotBlank(message = "Field cannot be empty") @Email(message = "Invalid email address") String email,
                            @Pattern(regexp = "^\\d{10}$", message = "Must be 10 digits") String mobileNumber,
                            @Size(min = 5, max = 255, message = "Must be between 5 and 255 characters") String message, String status,
                            Instant createdAt, String createdBy, Instant updatedAt, String updatedBy) {

    public Contact toContact() {
        return Contact.builder()
                .contactId(contactId)
                .name(name)
                .email(email)
                .mobileNumber(mobileNumber)
                .message(message)
                .status(status)
                .build();
    }
}
