package ua.pp.darknsoft.darkystore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "roles")
    private Set<Customer> customers = new LinkedHashSet<>();

    // Хелпер-методи тут зазвичай роблять двосторонню синхронізацію навпаки,
    // якщо вам зручно додавати юзера через об'єкт Role:

    public void addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.getRoles().add(this);
    }

    public void removeCustomer(Customer customer) {
        this.customers.remove(customer);
        customer.getRoles().remove(this);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Role role)) return false;

        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}