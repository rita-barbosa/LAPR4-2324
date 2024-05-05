package jobs4u.base.requirementsmanagement.dto;

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
