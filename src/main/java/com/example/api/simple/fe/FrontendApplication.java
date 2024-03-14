package com.example.api.simple.fe;

import com.example.api.SimpleJobRequest;
import org.jobrunr.scheduling.JobRequestScheduler;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
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
        var asapJobId = scheduler.enqueue(new SimpleJobRequest("Hello, now!", false));
        var failingJobId = scheduler.enqueue(new SimpleJobRequest("Hello, now!", true));
        var scheduledJobId = scheduler.schedule(Instant.now().plus(30, TimeUnit.SECONDS.toChronoUnit()),
                new SimpleJobRequest("Hello, later!", false));
        Map.of( "failing", failingJobId, "asap", asapJobId, "scheduled", scheduledJobId).forEach((k, v) -> System.out.println(k + '=' + v));
    }


    @Bean
    ApplicationRunner demo(JobRequestScheduler scheduler) {
        return args -> {
            graalvm(scheduler);
        };
    }

}
