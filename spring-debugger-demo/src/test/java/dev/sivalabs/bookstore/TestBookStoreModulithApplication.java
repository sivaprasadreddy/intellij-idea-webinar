package dev.sivalabs.bookstore;

import org.springframework.boot.SpringApplication;

public class TestBookStoreModulithApplication {

    public static void main(String[] args) {
        SpringApplication.from(BookStoreModulithApplication::main)
                .with(TestcontainersConfiguration.class)
                .withAdditionalProfiles("test")
                .run(args);
    }
}
