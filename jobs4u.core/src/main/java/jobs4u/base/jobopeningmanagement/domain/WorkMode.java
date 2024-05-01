package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.representations.dto.DTOable;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;

public class WorkMode implements DTOable<WorkModeDTO>, ValueObject, AggregateRoot<String> {

    private String denomination;

    public WorkMode(String denomination) {
        this.denomination = denomination;
    }

    public static WorkMode valueOf(final String workModeDenomination) {
        return new WorkMode(workModeDenomination);
    }

    @Override
    public WorkModeDTO toDTO() {
        return null;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public String identity() {
        return null;
    }
}
