package com.example.api.simplebatch.fe;

import com.example.api.SimpleBatchJobRequest;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobRequestScheduler;
import org.jobrunr.storage.sql.postgres.PostgresStorageProvider;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
public class FrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }

    @Bean
    ApplicationRunner demo(JobRequestScheduler scheduler) {
        return args -> {
            var jobId = scheduler.enqueue(new SimpleBatchJobRequest( "simpleJob" , Map.of("date" , new Date(),
                    "fileName" , UUID.randomUUID() + ".csv") ));
            System.out.println("enqueued work with jobID [" + jobId +
                    "]  ");

        };
    }

}
