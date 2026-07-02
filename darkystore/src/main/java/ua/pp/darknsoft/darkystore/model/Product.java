package ua.pp.darknsoft.darkystore.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import ua.pp.darknsoft.darkystore.dto.ProductRecord;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "name", nullable = false, length = 250)
    private String name;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "popularity", nullable = false)
    private Integer popularity;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

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

    public ProductRecord toRecord() {
        return new ProductRecord(productId, name, description, price, popularity, imageUrl, createdAt, createdBy, updatedAt, updatedBy);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product other)) return false;
        return this.productId != null && Objects.equals(this.productId, other.getProductId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
