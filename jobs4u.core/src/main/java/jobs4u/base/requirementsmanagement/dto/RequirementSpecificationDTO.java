package jobs4u.base.requirementsmanagement.dto;

import jobs4u.base.requirementsmanagement.domain.PluginJarFile;
import jobs4u.base.requirementsmanagement.domain.RequirementDescription;
import jobs4u.base.requirementsmanagement.domain.RequirementName;

public class RequirementSpecificationDTO {

    private final String name;
    private final String description;
    private String plugin;

    public RequirementSpecificationDTO(RequirementName requirementName, RequirementDescription description) {
        this.name = requirementName.name() ;
        this.description = description.description();
    }

    public RequirementSpecificationDTO(RequirementName requirementName, RequirementDescription description, PluginJarFile plugin) {
        this.name = requirementName.name() ;
        this.description = description.description();
        this.plugin = plugin.pluginName();
    }

    public String filename(){
        return this.name;
    }

    @Override
    public String toString() {
        if (plugin == null){
            return String.format("Name: %s\nDescription: %s", name, description);
        }
        return String.format("Name: %s | Plugin : %s |\nDescription: %s", name, plugin, description);
    }
}
