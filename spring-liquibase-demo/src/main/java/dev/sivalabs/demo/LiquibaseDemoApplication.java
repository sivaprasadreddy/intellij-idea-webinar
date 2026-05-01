package dev.sivalabs.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class LiquibaseDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiquibaseDemoApplication.class, args);
    }
}
