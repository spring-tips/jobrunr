package com.example.api.simplebatch.bg;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

@SpringBootApplication
public class BackgroundApplication {

    @Bean
    SimpleBatchJobRequestHandler batchJobRequestHandler(JobLauncher jobLauncher, Map<String, Job> batchJobs) {
        return new SimpleBatchJobRequestHandler(jobLauncher, batchJobs);
    }

    @Component
    static class MyTasklet implements Tasklet {

        @Override
        public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
            System.out.println("chunkContext: " + chunkContext.toString());
            System.out.println("contribution: " + contribution.toString());
            return RepeatStatus.FINISHED;
        }
    }

    @Bean
    Step step(JobRepository repository, MyTasklet tasklet, PlatformTransactionManager ptm) {
        return new StepBuilder("step", repository)
                .tasklet(tasklet, ptm)
                .build();
    }

    @Bean
    Job simpleJob(JobRepository repository, Step step) {
        return new JobBuilder("simpleJob", repository)
                .start(step)
                .build();
    }

    public static void main(String[] args) {
        System.setProperty("org.jobrunr.background-job-server.enabled", "true");
        SpringApplication.run(BackgroundApplication.class, args);
    }
}

