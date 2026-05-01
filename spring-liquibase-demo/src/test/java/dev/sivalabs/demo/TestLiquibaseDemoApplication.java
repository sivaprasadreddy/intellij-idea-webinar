package dev.sivalabs.demo;

import org.springframework.boot.SpringApplication;

public class TestLiquibaseDemoApplication {

    public static void main(String[] args) {
        System.setProperty("spring.docker.compose.enabled", "false");
        SpringApplication.from(LiquibaseDemoApplication::main)
                .with(TestcontainersConfig.class)
                .run(args);
    }
}
