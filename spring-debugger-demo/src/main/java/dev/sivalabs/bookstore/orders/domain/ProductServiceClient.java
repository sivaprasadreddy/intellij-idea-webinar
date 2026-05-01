package dev.sivalabs.bookstore.orders.domain;

import dev.sivalabs.bookstore.catalog.CatalogAPI;
import dev.sivalabs.bookstore.catalog.domain.model.ProductDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductServiceClient {
    private final CatalogAPI catalogAPI;

    public ProductServiceClient(CatalogAPI catalogAPI) {
        this.catalogAPI = catalogAPI;
    }

    public void validate(String productCode, BigDecimal price) {
        ProductDto product = catalogAPI
                .getByCode(productCode)
                .orElseThrow(() -> new InvalidOrderException("Product not found with code: " + productCode));
        if (product.price().compareTo(price) != 0) {
            throw new InvalidOrderException("Product price mismatch");
        }
    }
}
