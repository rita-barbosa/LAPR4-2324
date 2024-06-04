package jobs4u.base.applicationmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

@Embeddable
public class ApplicationStatus implements ValueObject {

    private String statusDescription;

    protected ApplicationStatus(){
        //for ORM
    }

    public ApplicationStatus(ApplicationStatusEnum enumVal){
        Preconditions.noneNull(enumVal);
        this.statusDescription = String.valueOf(enumVal);
    }

    public void updateStatusDescriptionAsNOT_CHECKED(){
        this.statusDescription = String.valueOf(ApplicationStatusEnum.NOT_CHECKED);
    }
    public void updateStatusDescriptionAsACCEPTED(){
        this.statusDescription = String.valueOf(ApplicationStatusEnum.ACCEPTED);
    }

    public void updateStatusDescriptionAsREJECTED(){
        this.statusDescription = String.valueOf(ApplicationStatusEnum.REJECTED);
    }

    public static ApplicationStatus valueOf(ApplicationStatusEnum enumVal){
        return new ApplicationStatus(enumVal);
    }

    public String getStatusDescription(){
        return statusDescription;
    }

}
