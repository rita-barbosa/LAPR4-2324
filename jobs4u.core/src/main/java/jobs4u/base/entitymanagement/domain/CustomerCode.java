package jobs4u.base.entitymanagement.domain;

import eapli.framework.actions.Actions;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class CustomerCode implements Comparable<CustomerCode>, ValueObject {

    private String code;


    protected CustomerCode() {
        this.code = null;
    }

    public CustomerCode(String code) {
        Preconditions.noneNull(code);
        Preconditions.nonEmpty(code);
        Actions.doIf(Actions.THROW_ARGUMENT, (code.length() >= 10));

        this.code = code;
    }

    @Override
    public int compareTo(CustomerCode o) {
        return this.code.compareTo(o.code);
    }

    @Override
    public String toString() {
        return this.code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerCode that = (CustomerCode) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
