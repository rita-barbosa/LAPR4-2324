package jobs4u.base.recruitmentprocessmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

@Embeddable
public class PhaseType implements ValueObject {

    private String typeDenomination;

    protected PhaseType(){
        //for ORM
    }

    public PhaseType(String typeDenomination) {
        Preconditions.noneNull(typeDenomination);
        this.typeDenomination = typeDenomination;
    }

    public PhaseType(PhaseTypeEnum typeDenomination) {
        Preconditions.noneNull(typeDenomination);
        this.typeDenomination = String.valueOf(typeDenomination);
    }

    public String getTypeDenomination() {
        return typeDenomination;
    }

    public void setTypeDescriptionAsAPPLICATION(){
        this.typeDenomination = String.valueOf(PhaseTypeEnum.APPLICATION);
    }
    public void setTypeDescriptionAsSCREENING(){
        this.typeDenomination = String.valueOf(PhaseTypeEnum.SCREENING);
    }

    public void setTypeDescriptionAsINTERVIEWS(){
        this.typeDenomination = String.valueOf(PhaseTypeEnum.INTERVIEWS);
    }

    public void setTypeDescriptionAsANALYSIS(){
        this.typeDenomination = String.valueOf(PhaseTypeEnum.ANALYSIS);
    }

    public void setTypeDescriptionAsRESULT(){
        this.typeDenomination = String.valueOf(PhaseTypeEnum.RESULT);
    }


}
