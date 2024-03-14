package com.example.api.simplebg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class BackgroundApplication {

    @Bean
    SimpleJobRequestHandler simpleJobRequestHandler(CustomerService customerService) {
        return new SimpleJobRequestHandler(customerService);
    }

    public static void main(String[] args) {
        System.setProperty("org.jobrunr.background-job-server.enabled", "true");
        SpringApplication.run(BackgroundApplication.class, args);
    }
}

@Service
class CustomerService {

    Customer byId(Long id) {
        return new Customer(id, Math.random() > .5 ? "A" : "B");
    }
}

record Customer(Long id, String name) {
}