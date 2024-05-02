package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.representations.dto.DTOable;
import jakarta.persistence.Embeddable;

@Embeddable
public class JobFunction implements ValueObject {

    private String jobFunction;

    public JobFunction(String jobFunction) {
        this.jobFunction = jobFunction;
    }

    public JobFunction() {
        //for ORM
    }

    public static JobFunction valueOf(final String jobFunction) {
        return new JobFunction(jobFunction);
    }


}
