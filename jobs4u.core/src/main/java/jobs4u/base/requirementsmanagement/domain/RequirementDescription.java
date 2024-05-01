package jobs4u.base.requirementsmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class RequirementDescription implements ValueObject {

    private final String description;

    public RequirementDescription(String description) {
        Preconditions.noneNull(description);
        Preconditions.nonEmpty(description);
        this.description = description;
    }

    public String description(){
        return this.description;
    }
}
