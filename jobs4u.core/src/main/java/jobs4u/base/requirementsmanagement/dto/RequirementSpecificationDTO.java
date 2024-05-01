package jobs4u.base.requirementsmanagement.dto;

import jobs4u.base.requirementsmanagement.domain.PluginJarFile;
import jobs4u.base.requirementsmanagement.domain.RequirementDescription;
import jobs4u.base.requirementsmanagement.domain.RequirementName;

public class RequirementSpecificationDTO {

    private String filename;
    private String description;
    private String plugin;

    public RequirementSpecificationDTO(RequirementName requirementName, RequirementDescription description) {
        this.filename = requirementName.name() ;
        this.description = description.description();
    }

    public RequirementSpecificationDTO(RequirementName requirementName, RequirementDescription description, PluginJarFile plugin) {
        this.filename = requirementName.name() ;
        this.description = description.description();
        this.plugin = plugin.pluginName();
    }

    public String filename(){
        return this.filename;
    }
}
