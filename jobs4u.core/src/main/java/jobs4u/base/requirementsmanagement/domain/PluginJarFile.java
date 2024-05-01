package jobs4u.base.requirementsmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class PluginJarFile implements ValueObject {

    private final String pluginName;

    public PluginJarFile(String pluginName) {
        Preconditions.noneNull(pluginName);
        Preconditions.nonEmpty(pluginName);
        this.pluginName = pluginName;
    }

    public String pluginName(){
        return this.pluginName;
    }
}
