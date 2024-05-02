package jobs4u.base.requirementsmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

@Embeddable
public class RequirementDescription implements ValueObject {

    private String description;

    public RequirementDescription(String description) {
        Preconditions.noneNull(description);
        Preconditions.nonEmpty(description);
        this.description = description;
    }

    public RequirementDescription() {
        //for ORM
    }

    public String description(){
        return this.description;
    }
}
