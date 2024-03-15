package shared;

import org.jobrunr.jobs.lambdas.JobRequestHandler;

public class MyJobRequestHandler implements JobRequestHandler<MyJobRequest> {

    @Override
    public void run(MyJobRequest myJobRequest) throws Exception {
        System.out.println("hello, " + myJobRequest.name() + "!");
        throw new RuntimeException("oops! couldn't process the request!");
    }
}
