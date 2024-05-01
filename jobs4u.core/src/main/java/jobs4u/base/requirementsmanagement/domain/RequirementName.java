package jobs4u.base.requirementsmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class RequirementName implements ValueObject, Comparable<RequirementName> {

    private final String name;

    public RequirementName(String name) {
        Preconditions.noneNull(name);
        Preconditions.nonEmpty(name);

        this.name = name;
    }

    @Override
    public int compareTo(RequirementName o) {
        return 0;
    }

    public String name() {
        return name;
    }
}
