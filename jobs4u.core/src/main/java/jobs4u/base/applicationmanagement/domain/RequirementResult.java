package jobs4u.base.applicationmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import java.util.Objects;

public class RequirementResult implements ValueObject {
    private Boolean requirementResult;

    public RequirementResult(Boolean answer){
        Preconditions.noneNull(answer);
        this.requirementResult = answer;
    }

    public RequirementResult(){
        //for ORM
    }

    public Boolean requirementResult(){
        return requirementResult;
    }

    public static RequirementResult valueOf(final Boolean answer){
        return new RequirementResult(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequirementResult)) return false;
        RequirementResult that = (RequirementResult) o;
        return Objects.equals(requirementResult, that.requirementResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requirementResult);
    }
}
