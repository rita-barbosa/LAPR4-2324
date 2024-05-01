package jobs4u.base.jobopeningmanagement.domain;


import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class JobReference implements Comparable<JobReference>, ValueObject {

    public JobReference() {
        // FOR ORM
    }

   private String costumerCode;

    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer sequentialCode;

    public JobReference(String costumerCode, Integer sequentialCode) {
        this.costumerCode = costumerCode;
        this.sequentialCode = sequentialCode;
    }


    @Override
    public int compareTo(JobReference o) {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s-%d", costumerCode, sequentialCode);
    }

    public String getCostumerCode() {
        return costumerCode;
    }

    public Integer getSequentialCode() {
        return sequentialCode;
    }
}
