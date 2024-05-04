package jobs4u.base.applicationmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import java.util.Objects;

public class RequirementAnswer implements ValueObject {

    private String requirementAnswer;

    public RequirementAnswer(String answer){
        Preconditions.noneNull(answer);
        Preconditions.nonEmpty(answer);
        this.requirementAnswer = answer;
    }

    public RequirementAnswer(){
        //for ORM
    }

    public String requirementAnswer(){
        return requirementAnswer;
    }

    public static RequirementAnswer valueOf(final String answer){
        return new RequirementAnswer(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequirementAnswer)) return false;
        RequirementAnswer that = (RequirementAnswer) o;
        return Objects.equals(requirementAnswer, that.requirementAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requirementAnswer);
    }
}
