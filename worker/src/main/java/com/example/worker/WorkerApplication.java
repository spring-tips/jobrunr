package com.example.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import shared.MyJobRequestHandler;

@SpringBootApplication
public class WorkerApplication {

    @Bean
    MyJobRequestHandler jobRequestHandler() {
        return new MyJobRequestHandler();
    }

    public static void main(String[] args) {
        SpringApplication.run(WorkerApplication.class, args);
    }

}
