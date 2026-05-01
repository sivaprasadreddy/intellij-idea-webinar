package dev.sivalabs.bookstore.orders.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderCreatedEvent(
        UUID orderNumber,
        BigDecimal totalAmount,
        Customer customer) {
}
