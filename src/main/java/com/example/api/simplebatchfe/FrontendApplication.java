package com.example.api.simplebatchfe;

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

@ImportRuntimeHints(FrontendApplication.Hints.class)
@SpringBootApplication
public class FrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }

    static class Hints implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            Set.of(org.jobrunr.jobs.details.CachingJobDetailsGenerator.class,
                    PostgresStorageProvider.class).forEach(cn ->
                    hints.reflection().registerType(cn, MemberCategory.values()));
        }
    }

    private static void jre() {
        var job = BackgroundJob.<GreetingsService>enqueue(GreetingsService::doSomething);
        System.out.println(job.asUUID());
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

    @Service
    public static class GreetingsService {

        public void doSomething() throws Exception {
            System.out.println("something is happening!");
        }
    }
}
