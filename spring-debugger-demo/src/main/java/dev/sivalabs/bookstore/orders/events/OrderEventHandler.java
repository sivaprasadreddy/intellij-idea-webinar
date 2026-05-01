package dev.sivalabs.bookstore.orders.events;

import dev.sivalabs.bookstore.ApplicationProperties;
import dev.sivalabs.bookstore.orders.domain.model.OrderCreatedEvent;
import dev.sivalabs.bookstore.notification.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class OrderEventHandler {
    private static final Logger log = LoggerFactory.getLogger(OrderEventHandler.class);

    private final EmailService emailService;
    private final ApplicationProperties properties;

    OrderEventHandler(EmailService emailService, ApplicationProperties properties) {
        this.emailService = emailService;
        this.properties = properties;
    }

    @EventListener
    void handle(OrderCreatedEvent event) {
        log.info("Order placed with order-number:{}, order total-amount: {}",
                event.orderNumber(), event.totalAmount());
        emailService.send(properties.supportEmail(), "New Order Placed",
                "Order placed with order-number:%s, order total-amount: %s".formatted(
                event.orderNumber(), event.totalAmount()));
    }
}
