package com.example.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@ImportRuntimeHints(ApiApplication.Hints.class)
@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
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

    @Component
    public static class Callback implements JobRequestHandler<MyJobRequest> {

        @Override
        public void run(MyJobRequest jobRequest) throws Exception {
            System.out.println("going to run with token [" + jobRequest.token() +
                    "]");
        }
    }

    public static class MyJobRequest implements JobRequest {


        private final String token;

        public String token() {
            return token;
        }

        @JsonCreator
        public MyJobRequest(@JsonProperty("token") String token) {
            this.token = token;
        }

        @Override
        public Class<? extends JobRequestHandler> getJobRequestHandler() {
            return Callback.class;
        }
    }

    @Bean
    ApplicationRunner demo(JobRequestScheduler scheduler) {
        return args -> {

            var key = UUID.randomUUID().toString();
            System.out.println("sending key [" + key +
                    "]");
            var jobId = scheduler.enqueue(new MyJobRequest(key));
            System.out.println(jobId);
        };
    }

    @Service
    public static class GreetingsService {

        public void doSomething() throws Exception {
            System.out.println("something is happening!");
        }
    }
}
