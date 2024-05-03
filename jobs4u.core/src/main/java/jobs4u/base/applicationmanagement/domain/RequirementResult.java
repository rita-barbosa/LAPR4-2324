package jobs4u.base.applicationmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import java.util.Objects;

public class RequirementResult implements ValueObject {
    private String RequirementResult;

    public RequirementResult(String answer){
        Preconditions.noneNull(answer);
        Preconditions.nonEmpty(answer);
        this.RequirementResult = answer;
    }

    public RequirementResult(){
        //for ORM
    }

    public static RequirementResult valueOf(final String answer){
        return new RequirementResult(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequirementResult)) return false;
        RequirementResult that = (RequirementResult) o;
        return Objects.equals(RequirementResult, that.RequirementResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(RequirementResult);
    }
}
