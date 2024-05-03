package jobs4u.base.customermanagement.dto;

import jobs4u.base.customermanagement.domain.CompanyName;
import jobs4u.base.customermanagement.domain.CustomerCode;

public class CustomerDTO {
    private final String custumerCode;
    private final String companyName;

    public CustomerDTO(CompanyName companyName, CustomerCode customerCode) {
        this.custumerCode = customerCode.customerCode();
        this.companyName = companyName.companyName();
    }

    public CustomerDTO(String custumerCode, String companyName) {
        this.custumerCode = custumerCode;
        this.companyName = companyName;
    }

    public String companyName() {
        return this.companyName;
    }

    public String customerCode() {
        return this.custumerCode;
    }

    @Override
    public String toString() {
        return String.format("%s | %s", companyName, custumerCode);
    }
}
