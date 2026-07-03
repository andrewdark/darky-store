package ua.pp.darknsoft.darkystore.dto;

import ua.pp.darknsoft.darkystore.model.Contact;

import java.time.Instant;

public record ContactRecord(Long contactId, String name, String email, String mobileNumber, String message, Instant createdAt, String createdBy, Instant updatedAt, String updatedBy) {

    public Contact toContact() {
        return Contact.builder()
                .contactId(contactId)
                .name(name)
                .email(email)
                .mobileNumber(mobileNumber)
                .message(message)
                .createdAt(createdAt)
                .createdBy(createdBy)
                .updatedAt(updatedAt)
                .updatedBy(updatedBy)
                .build();
    }
}
