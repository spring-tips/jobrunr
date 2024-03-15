package shared;


import org.jobrunr.jobs.lambdas.JobRequest;

public class SpringBatchLaunchingJobRequest
        implements JobRequest {

    @Override
    public Class<SpringBatchLaunchingJobRequestHandler> getJobRequestHandler() {
        return SpringBatchLaunchingJobRequestHandler.class;
    }
}


