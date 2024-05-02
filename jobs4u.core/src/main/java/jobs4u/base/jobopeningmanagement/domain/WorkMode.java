package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;

@Entity
@Table(name = "T_WORKMODE")
public class WorkMode implements DTOable<WorkModeDTO>, ValueObject, AggregateRoot<String> {

    @Id
    private String denomination;

    public WorkMode(String denomination) {
        this.denomination = denomination;
    }

    public WorkMode() {
        //for ORM
    }

    public static WorkMode valueOf(final String workModeDenomination) {
        Preconditions.noneNull(workModeDenomination);
        Preconditions.nonEmpty(workModeDenomination);
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
