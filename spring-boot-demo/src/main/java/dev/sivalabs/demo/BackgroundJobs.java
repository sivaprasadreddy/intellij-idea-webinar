package dev.sivalabs.demo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class BackgroundJobs {

    @Async
    public void doSomething() {
        // your logic goes here
    }
}
