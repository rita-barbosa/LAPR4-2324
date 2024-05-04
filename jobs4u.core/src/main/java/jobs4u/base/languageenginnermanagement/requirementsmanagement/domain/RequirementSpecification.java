package jobs4u.base.languageenginnermanagement.requirementsmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.dto.RequirementSpecificationDTO;

import java.util.Objects;

@Entity
@Table(name = "T_REQUIREMENTSPECIFICATION")
public class RequirementSpecification implements DTOable<RequirementSpecificationDTO>, AggregateRoot<RequirementName> {

    @EmbeddedId
    private RequirementName requirementName;

    private RequirementDescription description;

    private FullClassName plugin;

    public RequirementSpecification(String requirementName, String description, String plugin) {
        Preconditions.noneNull(requirementName, description, plugin);
        Preconditions.nonEmpty(requirementName);
        Preconditions.nonEmpty(description);
        Preconditions.nonEmpty(plugin);
        this.requirementName = new RequirementName(requirementName);
        this.description = new RequirementDescription(description);
        this.plugin = new FullClassName(plugin);
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

    public FullClassName pluginJarFile() {
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
