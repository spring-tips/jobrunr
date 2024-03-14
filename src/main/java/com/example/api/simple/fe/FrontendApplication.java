package com.example.api.simple.fe;

import com.example.api.SimpleJobRequest;
import org.jobrunr.jobs.AbstractJob;
import org.jobrunr.jobs.filters.JobClientFilter;
import org.jobrunr.jobs.filters.JobFilter;
import org.jobrunr.scheduling.JobRequestScheduler;
import org.jobrunr.storage.StorageProvider;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class FrontendApplication {

    public static void main(String[] args) {
        System.setProperty("org.jobrunr.dashboard.enabled", "true");
        SpringApplication.run(FrontendApplication.class, args);
    }

    /*
        @Service
        public static class GreetingsService {
            public void doSomething() throws Exception {
                System.out.println("something is happening!");
            }
        }

        private static void jre() {
            var job = BackgroundJob.<GreetingsService>enqueue(GreetingsService::doSomething);
            System.out.println(job.asUUID());
        }
    */

    private static void graalvm(JobRequestScheduler scheduler) {
        Map.of(
//                "failing", scheduler.enqueue(new SimpleJobRequest("Hello, now!", true)),
                        "asap", scheduler.enqueue(new SimpleJobRequest("Hello, now!", false)),
                        "scheduled", scheduler.schedule(Instant.now().plus(30, TimeUnit.SECONDS.toChronoUnit()),
                                new SimpleJobRequest("Hello, later!", false)))
                .forEach((k, v) -> System.out.println(k + '=' + v));
    }

    @Component
    static class MyJobClientFilter implements JobClientFilter {

        @Override
        public void onCreating(AbstractJob job) {
            System.out.println("creating [" + job + "]");
        }

        @Override
        public void onCreated(AbstractJob job) {
            System.out.println("created [" + job + "]");
        }
    }


    @Bean
    JobRequestScheduler jobRequestScheduler(StorageProvider storageProvider, List<JobFilter> jobFilterMap) {
        System.out.println("building my own jobRequestScheduler with [" + jobFilterMap.size() + "] filters");
        return new JobRequestScheduler(storageProvider, jobFilterMap);
    }


    @Bean
    ApplicationRunner demo(JobRequestScheduler scheduler) {
        return args -> {
            graalvm(scheduler);
        };
    }

}
