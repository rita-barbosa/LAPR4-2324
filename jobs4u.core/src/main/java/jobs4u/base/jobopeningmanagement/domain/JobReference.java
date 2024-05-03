package jobs4u.base.jobopeningmanagement.domain;


import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class JobReference implements Comparable<JobReference>, ValueObject {

    private String companyCode;

    @Column(unique = true, nullable = false)
    private Integer sequentialCode;

    public JobReference() {
        // FOR ORM
    }

    public JobReference(String custumerCode, Integer sequentialCode) {
        Preconditions.noneNull(custumerCode, sequentialCode);
        Preconditions.noneNull(custumerCode);
        Preconditions.nonEmpty(custumerCode);
        Preconditions.isPositive(sequentialCode);
        this.companyCode = custumerCode;
        this.sequentialCode = sequentialCode;
    }

    @Override
    public int compareTo(JobReference other) {
        int customerCodeComparison = CharSequence.compare(this.companyCode, other.companyCode);
        if (customerCodeComparison != 0) {
            return customerCodeComparison;
        }
        return Integer.compare(this.sequentialCode, other.sequentialCode);
    }



    @Override
    public String toString() {
        return String.format("%s-%d", companyCode, sequentialCode);
    }

    public String getcustomerCode() {
        return companyCode;
    }

    public Integer getSequentialCode() {
        return sequentialCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobReference)) return false;
        JobReference that = (JobReference) o;
        return Objects.equals(companyCode, that.companyCode) && Objects.equals(getSequentialCode(), that.getSequentialCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyCode, getSequentialCode());
    }
}
