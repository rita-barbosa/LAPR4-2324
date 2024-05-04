package jobs4u.base.applicationmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.util.Date;
import java.util.Objects;

@Embeddable
public class Interview implements ValueObject {

    private String interviewTypeDenomination;
    private Date schedule;
    private InterviewResult interviewResult;
    private String interviewAnswer;


    public Interview(String interviewTypeDenomination, Date schedule, InterviewResult interviewResult, String interviewAnswer) {
        Preconditions.noneNull(interviewAnswer, schedule, interviewResult, interviewAnswer);
        Preconditions.nonEmpty(interviewTypeDenomination);
        Preconditions.nonEmpty(interviewAnswer);

        this.interviewTypeDenomination = interviewTypeDenomination;
        this.schedule = schedule;
        this.interviewResult = interviewResult;
        this.interviewAnswer = interviewAnswer;
    }

    protected Interview(){
        //for ORM
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interview that = (Interview) o;
        return Objects.equals(interviewTypeDenomination, that.interviewTypeDenomination) && Objects.equals(schedule, that.schedule) && Objects.equals(interviewResult, that.interviewResult) && Objects.equals(interviewAnswer, that.interviewAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interviewTypeDenomination, schedule, interviewResult, interviewAnswer);
    }
}
