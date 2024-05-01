package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Id;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;

public class WorkMode implements DTOable<WorkModeDTO>, ValueObject, AggregateRoot<String> {

    private final String denomination;

    public WorkMode(String denomination) {
        this.denomination = denomination;
    }

    public static WorkMode valueOf(final String workModeDenomination) {
        Preconditions.noneNull(workModeDenomination);
        return new WorkMode(workModeDenomination);
    }

    @Override
    public WorkModeDTO toDTO() {
        return new WorkModeDTO(this.denomination);
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
