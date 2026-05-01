package dev.sivalabs.bookstore.catalog.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProductDto(
        String code,
        String name,
        String description,
        String imageUrl,
        BigDecimal price) implements Serializable {
}
