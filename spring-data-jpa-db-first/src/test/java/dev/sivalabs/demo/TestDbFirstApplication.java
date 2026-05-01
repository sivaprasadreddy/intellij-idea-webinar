package dev.sivalabs.demo;

import org.springframework.boot.SpringApplication;

public class TestDbFirstApplication {

    public static void main(String[] args) {
        System.setProperty("spring.docker.compose.enabled", "false");
        SpringApplication
                .from(DbFirstApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }

}
