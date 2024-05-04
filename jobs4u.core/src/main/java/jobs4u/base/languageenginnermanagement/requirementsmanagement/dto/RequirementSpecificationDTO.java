package jobs4u.base.languageenginnermanagement.requirementsmanagement.dto;

import eapli.framework.representations.dto.DTO;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.FullClassName;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementDescription;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementName;
import lombok.AllArgsConstructor;

public class RequirementSpecificationDTO {

    private final String name;
    private final String description;
    private String plugin;

    public RequirementSpecificationDTO(String requirementName, String description) {
        this.name = requirementName;
        this.description = description;
    }

    public RequirementSpecificationDTO(String name, String description, String plugin) {
        this.name = name;
        this.description = description;
        this.plugin = plugin;
    }

    public String filename() {
        return this.name;
    }

    @Override
    public String toString() {
        if (plugin == null) {
            return String.format("Name: %s | Description: %s", name, description);
        }
        return String.format("Name: %s | Plugin : %s | Description: %s", name, plugin, description);
    }
}
