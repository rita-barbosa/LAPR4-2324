package jobs4u.base.languageenginnermanagement.interviewmodelmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jobs4u.base.interviewmodelmanagement.dto.InterviewModelDTO;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.FullClassName;

import java.util.Objects;

@Entity
@Table(name = "T_INTERVIEWMODEL")
public class InterviewModel implements AggregateRoot<InterviewModelName> {

    @EmbeddedId
    private InterviewModelName interviewModelName;

    private InterviewModelDescription description;

    private FullClassName plugin;

    public InterviewModel(String interviewModelName, String description, String fullClassName) {
        Preconditions.noneNull(interviewModelName,description,fullClassName);
        Preconditions.nonEmpty(interviewModelName);
        Preconditions.nonEmpty(description);
        Preconditions.nonEmpty(fullClassName);
        this.interviewModelName = new InterviewModelName(interviewModelName);
        this.description = new InterviewModelDescription(description);
        this.plugin = new FullClassName(fullClassName);
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

    public FullClassName pluginJarFile() {
        return plugin;
    }
    public String nameString(){
        return this.interviewModelName.name();
    }
    public String descriptionString(){
        return this.interviewModelName.name();
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

    public InterviewModelDTO toDto() {
        return  new InterviewModelDTO(nameString(), descriptionString());
    }
}
