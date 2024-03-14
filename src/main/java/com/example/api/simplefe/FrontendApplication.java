package com.example.api.simplefe;

import com.example.api.SimpleJobRequest;
import org.jobrunr.scheduling.JobRequestScheduler;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FrontendApplication {

    public static void main(String[] args) {
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
        var jobId = scheduler.enqueue(new SimpleJobRequest("Hello, world!"));
        System.out.println("enqueued work with jobID [" + jobId + "]  ");
    }


    @Bean
    ApplicationRunner demo(JobRequestScheduler scheduler) {
        return args -> graalvm(scheduler);
    }

}
