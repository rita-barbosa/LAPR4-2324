package jobs4u.base.customermanagement.dto;

import jobs4u.base.customermanagement.domain.CompanyName;
import jobs4u.base.customermanagement.domain.CustomerCode;

public class CustomerDTO {
    private String costumerCode;
    private String companyName;

    public CustomerDTO(CompanyName companyName, CustomerCode customerCode) {
        this.costumerCode = customerCode.costumerCode();
        this.companyName = companyName.companyName();
    }

    public String costumerCode(){
        return this.costumerCode;
    }
}
