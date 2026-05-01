package dev.sivalabs.bookstore.orders.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
class InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decreaseInventoryLevel(String productCode, int quantity) {
        log.info("decrease inventory level for {} by {}", productCode, quantity);
    }
}
