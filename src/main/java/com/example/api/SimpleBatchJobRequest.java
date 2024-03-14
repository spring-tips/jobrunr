package com.example.api;

import com.example.api.simplebatch.bg.SimpleBatchJobRequestHandler;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;

import java.util.Map;

/**
 * look up a Spring Batch job by its name and then run it
 */

public record SimpleBatchJobRequest(
        @JsonProperty("jobName") String jobName,
        @JsonProperty("arguments") Map<String, Object> arguments)
        implements JobRequest {

    @Override
    public Class<? extends JobRequestHandler<?>> getJobRequestHandler() {
        return SimpleBatchJobRequestHandler.class;
    }
}
