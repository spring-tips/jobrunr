package com.example.api.simplebatch.fe;

import com.example.api.SimpleBatchJobRequest;
import org.jobrunr.scheduling.JobRequestScheduler;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class FrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }

    @Bean
    ApplicationRunner demo(JobRequestScheduler scheduler) {
        return args -> {
            var arguments = Map.<String,Object>of("date", new Date(), "fileName", UUID.randomUUID() + ".csv");
            var jobRequest = new SimpleBatchJobRequest("simpleJob", arguments);
            var jobId = scheduler.enqueue(jobRequest);
            System.out.println("enqueued work with jobID [" + jobId + "]  ");

        };
    }

}
