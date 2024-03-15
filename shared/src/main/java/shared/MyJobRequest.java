package shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jobrunr.jobs.lambdas.JobRequest;

public class MyJobRequest implements JobRequest {

    private final String name;

    @JsonCreator
    public MyJobRequest(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String name () {
        return name;
    }

    @Override
    public Class<MyJobRequestHandler> getJobRequestHandler() {
        return MyJobRequestHandler.class;
    }
}
