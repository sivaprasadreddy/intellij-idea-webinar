package dev.sivalabs.bookstore.orders.domain;

import dev.sivalabs.bookstore.orders.domain.model.CreateOrderCmd;
import dev.sivalabs.bookstore.orders.domain.model.OrderCreatedEvent;
import dev.sivalabs.bookstore.orders.domain.model.OrderDto;
import dev.sivalabs.bookstore.orders.domain.model.OrderItem;
import dev.sivalabs.bookstore.shared.model.PagedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;
    private final OrderMapper orderMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final int ordersPerPage;
    private final InventoryService inventoryService;

    OrderService(OrderRepository orderRepository,
                 ProductServiceClient productServiceClient,
                 OrderMapper orderMapper,
                 ApplicationEventPublisher publisher,
                 @Value("${app.orders-per-page}") int ordersPerPage,
                 InventoryService inventoryService) {
        this.orderRepository = orderRepository;
        this.productServiceClient = productServiceClient;
        this.orderMapper = orderMapper;
        this.eventPublisher = publisher;
        this.ordersPerPage = ordersPerPage;
        this.inventoryService = inventoryService;
    }

    @Transactional
    public OrderEntity createOrder(CreateOrderCmd cmd) {
        validateOrderRequest(cmd);
        OrderEntity orderEntity = orderMapper.convertToEntity(cmd);
        OrderEntity order = orderRepository.save(orderEntity);
        log.info("Created Order with orderNumber={}", order.getOrderNumber());

        cmd.items().forEach(item -> inventoryService.decreaseInventoryLevel(item.code(), item.quantity()));

        var event = new OrderCreatedEvent(
                order.getOrderNumber(),
                order.getTotalAmount(),
                order.getCustomer());
        eventPublisher.publishEvent(event);
        return order;
    }

    @Transactional(readOnly = true)
    public Optional<OrderDto> findOrder(UUID orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber)
                .map(orderMapper::convertToDto);
    }

    @Transactional(readOnly = true)
    public PagedResult<OrderDto> findOrders(int page) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, ordersPerPage, sort);
        var ordersPage = orderRepository.findAllBy(pageable)
                            .map(orderMapper::convertToDto);
        return new PagedResult<>(ordersPage);
    }

    private void validateOrderRequest(CreateOrderCmd cmd) {
        for (OrderItem item : cmd.items()) {
            productServiceClient.validate(item.code(), item.price());
        }
    }
}
