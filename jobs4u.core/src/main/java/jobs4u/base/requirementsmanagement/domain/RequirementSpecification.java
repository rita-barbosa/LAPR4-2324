package jobs4u.base.requirementsmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jobs4u.base.requirementsmanagement.dto.RequirementSpecificationDTO;

import java.util.Objects;

@Entity
@Table(name = "T_REQUIREMENTSPECIFICATION")
public class RequirementSpecification implements DTOable<RequirementSpecificationDTO>, AggregateRoot<RequirementName> {

    @Id
    private RequirementName requirementName;

    private RequirementDescription description;

    private PluginJarFile plugin;

    public RequirementSpecification(RequirementName requirementName, RequirementDescription description, PluginJarFile plugin) {
        Preconditions.noneNull(requirementName, description, plugin);
        this.requirementName = requirementName;
        this.description = description;
        this.plugin = plugin;
    }

    protected RequirementSpecification() {
        //for ORM
    }

    @Override
    public RequirementSpecificationDTO toDTO() {
        return new RequirementSpecificationDTO(requirementName.name(), description.description(), plugin.pluginName());
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public RequirementName identity() {
        return this.requirementName;
    }

    public RequirementName requirementName() {
        return identity();
    }

    public RequirementDescription requirementDescription() {
        return description;
    }

    public PluginJarFile pluginJarFile() {
        return plugin;
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequirementSpecification)) return false;
        RequirementSpecification that = (RequirementSpecification) o;
        return Objects.equals(requirementName, that.requirementName) && Objects.equals(description, that.description) && Objects.equals(plugin, that.plugin);
    }
}
