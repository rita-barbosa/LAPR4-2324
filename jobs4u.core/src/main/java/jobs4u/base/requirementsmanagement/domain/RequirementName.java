package jobs4u.base.requirementsmanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class RequirementName implements ValueObject, Comparable<RequirementName> {

    private String name;

    @Override
    public int compareTo(RequirementName o) {
        return 0;
    }

    public String getName() {
        return name;
    }
}
