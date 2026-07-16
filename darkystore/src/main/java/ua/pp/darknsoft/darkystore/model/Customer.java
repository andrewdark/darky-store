package ua.pp.darknsoft.darkystore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 100)
    @NotNull
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 15)
    @NotNull
    @Column(name = "mobile_number", nullable = false, length = 15)
    private String mobileNumber;

    @Size(max = 500)
    @NotNull
    @Column(name = "password_hash", nullable = false, length = 500)
    private String passwordHash;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Address address;

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Customer customer)) return false;

        return email.equals(customer.email) && mobileNumber.equals(customer.mobileNumber);
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + mobileNumber.hashCode();
        return result;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    Set<Role> roles = new LinkedHashSet<>();


}
