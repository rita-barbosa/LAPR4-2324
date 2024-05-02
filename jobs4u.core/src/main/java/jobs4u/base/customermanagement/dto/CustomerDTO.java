package jobs4u.base.customermanagement.dto;

import jobs4u.base.customermanagement.domain.CompanyName;
import jobs4u.base.customermanagement.domain.CustomerCode;

public class CustomerDTO {
    private final String custumerCode;
    private final String companyName;

    public CustomerDTO(CompanyName companyName, CustomerCode customerCode) {
        this.custumerCode = customerCode.costumerCode();
        this.companyName = companyName.companyName();
    }
    public String companyName(){
        return this.companyName;
    }

    public String customerCode(){
        return this.custumerCode;
    }

    @Override
    public String toString() {
        return String.format("%s | %s", companyName, custumerCode);
    }
}
