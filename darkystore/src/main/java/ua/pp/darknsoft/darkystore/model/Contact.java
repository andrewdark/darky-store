package ua.pp.darknsoft.darkystore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ua.pp.darknsoft.darkystore.dto.ContactRecord;

import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", nullable = false)
    private Long contactId;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank
    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;


    @NotBlank
    @Column(name = "message", nullable = false)
    private String message;

    public ContactRecord toRecord() {
        return new ContactRecord(contactId, name, email, mobileNumber, message, super.getCreatedAt(), super.getCreatedBy(), super.getUpdatedAt(), super.getUpdatedBy());
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact other)) return false;
        return this.contactId != null && Objects.equals(this.contactId, other.getContactId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
