package dev.sivalabs.bookstore.notification;

import dev.sivalabs.bookstore.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConditionalOnProperty(name = "app.email-service-type", havingValue = "console")
class ConsoleLoggingEmailService implements EmailService {
    private static final Logger log = LoggerFactory.getLogger(ConsoleLoggingEmailService.class);
    private final ApplicationProperties properties;

    ConsoleLoggingEmailService(ApplicationProperties properties) {
        this.properties = properties;
    }

    public void send(String to, String subject, String content) {
        this.send(List.of(to), subject, content);
    }

    public void send(List<String> to, String subject, String content) {
        String supportEmail = properties.supportEmail();
        String email = """
                ======================================================
                From: %s
                To: %s
                Subject: %s
                
                %s
                ======================================================
                """.formatted(supportEmail, to, subject, content);
        log.info(email);
    }
}