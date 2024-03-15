package com.example.leader;

import org.jobrunr.scheduling.JobRequestScheduler;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import shared.MyJobRequest;

@SpringBootApplication
public class LeaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeaderApplication.class, args);
    }

    @Bean
    ApplicationRunner leader(JobRequestScheduler jobRequestScheduler) {
        return args -> {
            jobRequestScheduler.enqueue(new MyJobRequest("Spring fans"));
        };
    }


}
