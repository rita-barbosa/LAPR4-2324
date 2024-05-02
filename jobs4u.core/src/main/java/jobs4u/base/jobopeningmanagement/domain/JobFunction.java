package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class JobFunction implements ValueObject {

    private String jobFunction;

    public JobFunction(String jobFunction) {
        Preconditions.noneNull(jobFunction);
        Preconditions.nonEmpty(jobFunction);
        this.jobFunction = jobFunction;
    }

    public JobFunction() {
        //for ORM
    }

    public static JobFunction valueOf(final String jobFunction) {
        return new JobFunction(jobFunction);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobFunction)) return false;
        JobFunction that = (JobFunction) o;
        return Objects.equals(jobFunction, that.jobFunction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobFunction);
    }
}
