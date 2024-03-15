package shared;

import org.jobrunr.jobs.lambdas.JobRequestHandler;

import java.util.Map;

/**
 *
 */
public class SpringBatchLaunchingJobRequestHandler
        implements JobRequestHandler<SpringBatchLaunchingJobRequest> {

    @Override
    public void run(SpringBatchLaunchingJobRequest request) throws Exception {

        var handler = request.getJobRequestHandler();
        var map = Map.of("handler", handler.getName(),
                "jobName", jobContext().getJobName(),
                "metadata", jobContext().getMetadata()
        );
        for (var k : map.keySet())
            System.out.println(k + '=' + map.get(k));

    }
}
