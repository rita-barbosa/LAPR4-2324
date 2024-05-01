package jobs4u.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;

public class CustomerCode implements Comparable<CustomerCode>, ValueObject {

    private final String costumerCode;

    public CustomerCode(String costumerCode) {
        this.costumerCode = costumerCode;
    }

    public String costumerCode(){
        return this.costumerCode;
    }

    @Override
    public int compareTo(CustomerCode o) {
        throw new UnsupportedOperationException("Yet to implement");
    }
}
