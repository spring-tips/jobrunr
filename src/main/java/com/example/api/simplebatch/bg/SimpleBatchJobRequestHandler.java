package com.example.api.simplebatch.bg;

import com.example.api.SimpleBatchJobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Map;

public class SimpleBatchJobRequestHandler
        implements JobRequestHandler<SimpleBatchJobRequest> {

    private final JobLauncher jobLauncher;

    private final Map<String, Job> springBatchJobs;

    public SimpleBatchJobRequestHandler(JobLauncher jobLauncher, Map<String, Job> springBatchJobs) {
        this.jobLauncher = jobLauncher;
        this.springBatchJobs = springBatchJobs;
    }

    @Override
    public void run(SimpleBatchJobRequest jobRequest) throws Exception {
        var jobName = jobRequest.jobName();
        var arguments = jobRequest.arguments();
        var jobParameters = new JobParametersBuilder();
        for (var entry : arguments.entrySet()) {
            var jobArgumentName = entry.getKey();
            var jobArgumentValue = entry.getValue();
            jobParameters = switch (jobArgumentValue) {
                case String stringValue -> jobParameters.addString(jobArgumentName, stringValue);
                case Date dateValue -> jobParameters.addDate(jobArgumentName, dateValue);
                default -> throw new IllegalStateException("it can only be one of a few well known, " +
                                "easy-to-serialize types! Don't be difficult, you insensitive clod!");
            };
        }
        //
        var job = this.springBatchJobs.get(jobName);
        Assert.notNull(job, "the  job named ['" + jobName + "'] does not exist!");
        //
        var execution = this.jobLauncher.run(job, jobParameters.toJobParameters());
        System.out.println("launched the job [" + execution + "]");


    }
}
