package dev.sivalabs.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ScheduledJobs {
    private static final Logger log = LoggerFactory.getLogger(ScheduledJobs.class);

    @Scheduled(cron = "${samplejob.cron}")
    public void doSomeWork() {
        log.info("Running sample job at {}", Instant.now());
    }
}
