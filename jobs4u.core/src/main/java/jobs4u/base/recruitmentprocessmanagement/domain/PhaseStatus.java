package jobs4u.base.recruitmentprocessmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;
import jobs4u.base.jobopeningmanagement.domain.JobOpeningStatus;
import jobs4u.base.jobopeningmanagement.domain.JobOpeningStatusEnum;

@Embeddable
public class PhaseStatus implements ValueObject {

    private String statusDescription;

    protected PhaseStatus(){
        //for ORM
    }

    public PhaseStatus(String statusDescription) {
        Preconditions.noneNull(statusDescription);
        this.statusDescription = String.valueOf(statusDescription);
    }

    public PhaseStatus(PhaseStatusEnum statusDescription) {
        Preconditions.noneNull(statusDescription);
        this.statusDescription = String.valueOf(statusDescription);
    }

    public void setStatusDescriptionAsPLANNED(){
        this.statusDescription = String.valueOf(PhaseStatusEnum.PLANNED);
    }
    public void setStatusDescriptionAsON_GOING(){
        this.statusDescription = String.valueOf(PhaseStatusEnum.ON_GOING);
    }

    public void setStatusDescriptionAsCONCLUDED(){
        this.statusDescription = String.valueOf(PhaseStatusEnum.CONCLUDED);
    }

    public static PhaseStatus valueOf(PhaseStatusEnum enumVal) {
        return new PhaseStatus(enumVal);
    }
}
