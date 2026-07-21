package ua.pp.darknsoft.darkystore.dto;

import ua.pp.darknsoft.darkystore.model.Contact;

public record ContactResponseDto(Long contactId, String name, String email,
                                 String mobileNumber, String message, String status) {

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
