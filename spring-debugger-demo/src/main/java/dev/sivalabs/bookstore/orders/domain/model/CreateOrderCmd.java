package dev.sivalabs.bookstore.orders.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateOrderCmd(
        @Valid @NotNull Customer customer,
        @NotEmpty String deliveryAddress,
        @Valid @NotEmpty List<OrderItem> items) {
}
