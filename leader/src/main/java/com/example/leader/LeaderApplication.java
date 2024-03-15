package com.example.leader;

import org.jobrunr.scheduling.JobRequestScheduler;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import shared.SpringBatchLaunchingJobRequest;

@SpringBootApplication
public class LeaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeaderApplication.class, args);
    }

    @Bean
    ApplicationRunner runner(JobRequestScheduler scheduler) {
        return args -> System.out.println(
                scheduler.enqueue(new SpringBatchLaunchingJobRequest()));
    }
}
