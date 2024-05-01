package jobs4u.base.customermanagement.domain;

import eapli.framework.actions.Actions;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class CustomerCode implements Comparable<CustomerCode>, ValueObject {

    private final String customerCode;

    public CustomerCode(String code) {
        Preconditions.noneNull(code);
        Preconditions.nonEmpty(code);
        Actions.doIf(Actions.THROW_ARGUMENT, (code.length() >= 10));

        this.customerCode = code;
    }

    public String costumerCode(){
        return this.customerCode;
    }

    @Override
    public int compareTo(CustomerCode o) {
        throw new UnsupportedOperationException("Yet to implement");
    }
}
