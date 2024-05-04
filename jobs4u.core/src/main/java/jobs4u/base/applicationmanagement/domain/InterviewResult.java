package jobs4u.base.applicationmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class InterviewResult  implements ValueObject {

    private String interviewResult;
    private Integer interviewGrade;
    private String justification;


    public InterviewResult(String interviewResult, Integer interviewGrade, String justification){
        this.interviewResult = interviewResult;
        this.interviewGrade = interviewGrade;
        this.justification = justification;
    }

    protected InterviewResult(){
        //for ORM
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterviewResult that = (InterviewResult) o;
        return Objects.equals(interviewResult, that.interviewResult) && Objects.equals(interviewGrade, that.interviewGrade) && Objects.equals(justification, that.justification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interviewResult, interviewGrade, justification);
    }
}
