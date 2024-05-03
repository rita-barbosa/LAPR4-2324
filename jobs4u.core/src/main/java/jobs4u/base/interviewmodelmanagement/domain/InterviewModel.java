package jobs4u.base.interviewmodelmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jobs4u.base.requirementsmanagement.domain.PluginJarFile;
import jobs4u.base.requirementsmanagement.dto.RequirementSpecificationDTO;

import java.util.Objects;

@Entity
@Table(name = "T_INTERVIEWMODEL")
public class InterviewModel implements AggregateRoot<InterviewModelName> {

    @Id
    private InterviewModelName interviewModelName;

    private InterviewModelDescription description;

    private PluginJarFile plugin;

    public InterviewModel(InterviewModelName interviewModelName, InterviewModelDescription description, PluginJarFile plugin) {
        Preconditions.noneNull(interviewModelName,description,plugin);
        this.interviewModelName = interviewModelName;
        this.description = description;
        this.plugin = plugin;
    }

    public InterviewModel() {
        //for ORM
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public InterviewModelName identity() {
        return this.interviewModelName;
    }

    public InterviewModelName interviewModelName() {
        return identity();
    }

    public InterviewModelDescription interviewModelDescription() {
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
        if (!(o instanceof InterviewModel)) return false;
        InterviewModel that = (InterviewModel) o;
        return Objects.equals(interviewModelName, that.interviewModelName) && Objects.equals(description, that.description) && Objects.equals(plugin, that.plugin);
    }
}
