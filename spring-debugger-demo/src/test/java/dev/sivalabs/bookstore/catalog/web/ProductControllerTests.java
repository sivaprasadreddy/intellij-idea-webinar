package dev.sivalabs.bookstore.catalog.web;

import dev.sivalabs.bookstore.TestcontainersConfiguration;
import dev.sivalabs.bookstore.catalog.domain.model.ProductDto;
import dev.sivalabs.bookstore.shared.model.PagedResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.client.RestTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
@AutoConfigureRestTestClient
@ActiveProfiles("test")
@Sql("/test-catalog-data.sql")
class ProductControllerTests {
    @Autowired
    protected RestTestClient restTestClient;

    @Test
    void shouldGetProducts() {
        PagedResult<ProductDto> pagedResult =
                restTestClient.get()
                .uri("/api/products")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<PagedResult<ProductDto>>(){})
                .returnResult().getResponseBody();

        assertThat(pagedResult).isNotNull();
        assertThat(pagedResult.totalElements()).isEqualTo(15);
        assertThat(pagedResult.pageNumber()).isEqualTo(1);
        assertThat(pagedResult.totalPages()).isEqualTo(2);
        assertThat(pagedResult.isFirst()).isTrue();
        assertThat(pagedResult.isLast()).isFalse();
        assertThat(pagedResult.hasNext()).isTrue();
        assertThat(pagedResult.hasPrevious()).isFalse();
        assertThat(pagedResult.data()).isNotNull();
    }

    @Test
    void shouldGetProductByCode() {
        ProductDto product = restTestClient.get()
                .uri("/api/products/{code}", "P100")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        assertThat(product).isNotNull();
        assertThat(product.code()).isEqualTo("P100");
        assertThat(product.name()).isEqualTo("The Hunger Games");
    }
}