package jobs4u.base.requirementsmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

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
}
