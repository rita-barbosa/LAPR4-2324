package jobs4u.base.requirementsmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PluginJarFile implements ValueObject {

    private String pluginName;

    public PluginJarFile(String pluginName) {
        Preconditions.noneNull(pluginName);
        Preconditions.nonEmpty(pluginName);
        this.pluginName = pluginName;
    }

    public PluginJarFile() {
        //for ORM
    }

    public String pluginName(){
        return this.pluginName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PluginJarFile)) return false;
        PluginJarFile that = (PluginJarFile) o;
        return Objects.equals(pluginName, that.pluginName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pluginName);
    }
}
