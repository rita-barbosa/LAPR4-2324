package jobs4u.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class CompanyName implements ValueObject {

    private String companyName;

    public CompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String companyName(){
        return this.companyName;
    }
}
