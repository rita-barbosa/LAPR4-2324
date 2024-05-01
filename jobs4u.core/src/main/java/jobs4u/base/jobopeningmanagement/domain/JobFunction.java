package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.representations.dto.DTOable;

public class JobFunction implements ValueObject {

    private final String jobFunction;

    public JobFunction(String jobFunction) {
        this.jobFunction = jobFunction;
    }

    public static JobFunction valueOf(final String jobFunction) {
        return new JobFunction(jobFunction);
    }


}
