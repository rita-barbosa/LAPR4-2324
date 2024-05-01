package jobs4u.base.requirementsmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.Getter;

@Getter
public class RequirementName implements ValueObject, Comparable<RequirementName> {

    private String name;

    @Override
    public int compareTo(RequirementName o) {
        return 0;
    }

}
