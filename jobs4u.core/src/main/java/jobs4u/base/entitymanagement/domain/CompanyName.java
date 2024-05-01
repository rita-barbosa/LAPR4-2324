package jobs4u.base.entitymanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class CompanyName implements ValueObject, Comparable<CompanyName> {

    private static final long serialVersionUID = 1L;
    private String name;

    protected CompanyName() {
    }

    public CompanyName(String name) {
        Preconditions.noneNull(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyName that = (CompanyName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(final CompanyName o) {
          return this.name.compareTo(o.name);
    }
}
