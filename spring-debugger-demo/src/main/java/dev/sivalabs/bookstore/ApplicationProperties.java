package dev.sivalabs.bookstore;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app")
public record ApplicationProperties(
        @NotEmpty
        String supportEmail,
        @NotEmpty
        String emailServiceType) {
}