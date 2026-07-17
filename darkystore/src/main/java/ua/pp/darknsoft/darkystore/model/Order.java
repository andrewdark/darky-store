package ua.pp.darknsoft.darkystore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new LinkedHashSet<>();

    @NotNull
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Size(max = 200)
    @NotNull
    @Column(name = "payment_id", nullable = false, length = 200)
    private String paymentId;

    @Size(max = 50)
    @NotNull
    @Column(name = "payment_status", nullable = false, length = 50)
    private String paymentStatus;

    @Size(max = 50)
    @NotNull
    @Column(name = "order_status", nullable = false, length = 50)
    private String orderStatus;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order other)) return false;
        return this.orderId != null && Objects.equals(this.orderId, other.getOrderId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
