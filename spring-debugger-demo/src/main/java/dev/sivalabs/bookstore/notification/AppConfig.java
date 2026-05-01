package dev.sivalabs.bookstore.notification;

import dev.sivalabs.bookstore.ApplicationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

//@Configuration
class AppConfig {

    @Bean
    @ConditionalOnProperty(name = "app.email-service-type", havingValue = "console")
    EmailService consoleLoggingEmailService(ApplicationProperties props) {
        return new ConsoleLoggingEmailService(props);
    }

    @Bean
    @ConditionalOnProperty(name = "app.email-service-type", havingValue = "javamail", matchIfMissing = true)
    EmailService javaMailService(JavaMailSender javaMailSender, ApplicationProperties props) {
        return new JavaMailService(javaMailSender, props);
    }
}
