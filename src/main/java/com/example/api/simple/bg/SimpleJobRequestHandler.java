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
        System.out.println("running [" + getClass().getName() +
                "] with jobRequest [" + jobRequest +
                "]");

        Assert.state(!jobRequest.fail() , "we've been told to fail!");


        var customer = this.service.byId(2L);
        System.out.println("got customer [" + customer + "]");
    }
}
