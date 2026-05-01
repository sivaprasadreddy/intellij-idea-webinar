package dev.sivalabs.bookstore.orders.domain;

import dev.sivalabs.bookstore.orders.domain.model.CreateOrderCmd;
import dev.sivalabs.bookstore.orders.domain.model.OrderDto;
import dev.sivalabs.bookstore.orders.domain.model.OrderItem;
import dev.sivalabs.bookstore.orders.domain.model.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMapper {

    public OrderEntity convertToEntity(CreateOrderCmd request) {
        OrderEntity entity = new OrderEntity();
        entity.setOrderNumber(UUID.randomUUID());
        entity.setStatus(OrderStatus.NEW);
        entity.setCustomer(request.customer());
        entity.setDeliveryAddress(request.deliveryAddress());
        request.items().forEach(item -> {
            entity.addOrderItem(this.toOrderItemEntity(entity, item));
        });
        return entity;
    }

    public OrderDto convertToDto(OrderEntity order) {
        return new OrderDto(
                order.getOrderNumber(),
                order.getItems().stream().map(this::toOrderItem).toList(),
                order.getCustomer(),
                order.getDeliveryAddress(),
                order.getStatus(),
                order.getCreatedAt());
    }

    private OrderItemEntity toOrderItemEntity(OrderEntity order, OrderItem orderItem) {
        OrderItemEntity orderItemEntity = new OrderItemEntity(
                orderItem.code(),
                orderItem.name(),
                orderItem.price(),
                orderItem.quantity()
        );
        orderItemEntity.setOrder(order);
        return orderItemEntity;
    }

    private OrderItem toOrderItem(OrderItemEntity orderItemEntity) {
        return new OrderItem(
                orderItemEntity.getProductCode(),
                orderItemEntity.getProductName(),
                orderItemEntity.getProductPrice(),
                orderItemEntity.getQuantity()
        );
    }
}
