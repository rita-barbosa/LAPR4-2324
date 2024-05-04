package jobs4u.base.languageenginnermanagement.requirementsmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class FullClassName implements ValueObject {

    private String pluginName;

    public FullClassName(String pluginName) {
        Preconditions.noneNull(pluginName);
        Preconditions.nonEmpty(pluginName);
        this.pluginName = pluginName;
    }

    public FullClassName() {
        //for ORM
    }

    public String pluginName(){
        return this.pluginName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FullClassName)) return false;
        FullClassName that = (FullClassName) o;
        return Objects.equals(pluginName, that.pluginName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pluginName);
    }
}
