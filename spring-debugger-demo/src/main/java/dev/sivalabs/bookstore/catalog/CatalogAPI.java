package dev.sivalabs.bookstore.catalog;

import dev.sivalabs.bookstore.catalog.domain.ProductService;
import dev.sivalabs.bookstore.catalog.domain.model.ProductDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CatalogAPI {
    private final ProductService productService;

    public CatalogAPI(ProductService productService) {
        this.productService = productService;
    }

    public Optional<ProductDto> getByCode(String productCode) {
        return productService.getByCode(productCode);
    }
}
