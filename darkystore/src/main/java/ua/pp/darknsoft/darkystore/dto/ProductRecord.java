package ua.pp.darknsoft.darkystore.dto;

import ua.pp.darknsoft.darkystore.model.Product;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductRecord(Long productId, String name, String description, BigDecimal price, Integer popularity,
                            String imageUrl, Instant createdAt,
                            String createdBy, Instant updatedAt, String updatedBy) {

    public Product toProduct() {
        return Product.builder()
                .productId(productId)
                .name(name)
                .description(description)
                .price(price)
                .popularity(popularity)
                .imageUrl(imageUrl)
                .build();
    }
}
