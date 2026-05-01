package dev.sivalabs.bookstore.catalog.domain;

import dev.sivalabs.bookstore.catalog.domain.model.ProductDto;
import org.springframework.stereotype.Component;

@Component
class ProductMapper {

    public ProductDto mapToDto(ProductEntity entity) {
        return new ProductDto(
                entity.getCode(), entity.getName(), entity.getDescription(), entity.getImageUrl(), entity.getPrice());
    }
}
