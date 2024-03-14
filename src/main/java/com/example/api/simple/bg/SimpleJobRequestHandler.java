package com.example.api.simple.bg;

import com.example.api.SimpleJobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.util.Assert;

public class SimpleJobRequestHandler
        implements JobRequestHandler<SimpleJobRequest> {

    private final CustomerService service;

    SimpleJobRequestHandler(CustomerService service) {
        this.service = service;
    }

    @Override
    public void run(SimpleJobRequest jobRequest) throws Exception {

        jobContext().progressBar(10);

        System.out.println("running [" + getClass().getName() + "] with jobRequest [" + jobRequest + "]");

        Thread.sleep(1000 * 2);
        jobContext().progressBar(50);

        Assert.state(!jobRequest.fail() , "we've been told to fail!");

        var customer = this.service.byId(2L);
        System.out.println("got customer [" + customer + "]");

        Thread.sleep(1000 * 20);
        jobContext().progressBar(90);

        var jobNameForMetadataKey = jobContext().getJobName() + "-finished";
        jobContext().saveMetadata(jobNameForMetadataKey, true);
        System.out.println("set metadata to true: " + jobNameForMetadataKey);
        jobContext().progressBar(100);
        jobContext().logger().warn("finished!");
    }
}
