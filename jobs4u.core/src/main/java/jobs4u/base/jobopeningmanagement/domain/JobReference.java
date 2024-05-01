package jobs4u.base.jobopeningmanagement.domain;


import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Embeddable
public class JobReference implements Comparable<JobReference>, ValueObject {

    public JobReference() {
        // FOR ORM
    }

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    String jobReference;
    @Override
    public int compareTo(JobReference o) {
        return 0;
    }
}
