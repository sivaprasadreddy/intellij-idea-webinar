package dev.sivalabs.bookstore.orders.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query("""
        select o
        from OrderEntity o join fetch o.items
        """)
    Page<OrderEntity> findAllBy(Pageable pageable);

    @Query("""
        select o
        from OrderEntity o join fetch o.items
        where o.orderNumber = :orderNumber
        """)
    Optional<OrderEntity> findByOrderNumber(UUID orderNumber);
}
