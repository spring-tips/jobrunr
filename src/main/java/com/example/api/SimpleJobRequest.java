package com.example.api;

import com.example.api.simple.bg.SimpleJobRequestHandler;
import org.jobrunr.jobs.lambdas.JobRequest;

public record SimpleJobRequest(String message ,boolean fail)
        implements JobRequest {

    @Override
    public Class<SimpleJobRequestHandler> getJobRequestHandler() {
        return SimpleJobRequestHandler.class;
    }
}
