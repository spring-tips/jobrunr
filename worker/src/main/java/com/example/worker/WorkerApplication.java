package com.example.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import shared.SpringBatchLaunchingJobRequestHandler;

@SpringBootApplication
public class WorkerApplication {

    @Bean
    SpringBatchLaunchingJobRequestHandler jobRequestHandler() {
        return new SpringBatchLaunchingJobRequestHandler();
    }

    
    public static void main(String[] args) {
        SpringApplication.run(WorkerApplication.class, args);
    }

}
