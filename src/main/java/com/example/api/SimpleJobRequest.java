package com.example.api;

import com.example.api.simplebg.SimpleJobRequestHandler;
import org.jobrunr.jobs.lambdas.JobRequest;

public record SimpleJobRequest(String message)
        implements JobRequest {

    @Override
    public Class<SimpleJobRequestHandler> getJobRequestHandler() {
        return SimpleJobRequestHandler.class;
    }
}
