package jobs4u.base.requirementsmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.dto.DTOable;
import jakarta.persistence.Embeddable;
import jobs4u.base.requirementsmanagement.dto.RequirementSpecificationDTO;

@Embeddable
public class RequirementSpecification implements DTOable<RequirementSpecificationDTO>, AggregateRoot<RequirementName> {

    private RequirementName name;

    private RequirementDescription description;

    private PluginJarFile plugin;

    @Override
    public RequirementSpecificationDTO toDTO() {
        return null;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public RequirementName identity() {
        return null;
    }

    public RequirementName getName() {
        return name;
    }

    public RequirementDescription getDescription() {
        return description;
    }

    public PluginJarFile getPlugin() {
        return plugin;
    }
}
