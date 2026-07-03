package ua.pp.darknsoft.darkystore.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import ua.pp.darknsoft.darkystore.dto.ContactRecord;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", nullable = false)
    private Long contactId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(name = "message", nullable = false)
    private String message;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "created_by", nullable = false, length = 20)
    private String createdBy;

    @ColumnDefault("NULL")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @ColumnDefault("NULL")
    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    public ContactRecord toRecord() {
        return new ContactRecord(contactId, name, email, mobileNumber, message, createdAt, createdBy, updatedAt, updatedBy);
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
