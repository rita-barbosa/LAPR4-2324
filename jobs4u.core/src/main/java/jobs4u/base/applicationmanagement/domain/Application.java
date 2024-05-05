package jobs4u.base.applicationmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.candidatemanagement.domain.Candidate;

import java.util.*;

@Entity
@Table(name = "T_APPLICATION")
public class Application implements AggregateRoot<Long>, DTOable<ApplicationDTO> {
    private static final long serialVersionUID = 1L;
    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
    private RequirementAnswer requirementAnswer;
//    @Column(nullable = false)
    private RequirementResult requirementResult;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ApplicationFile> files;
    @Column(nullable = false)
    private Date applicationDate;
    @Column(nullable = false)
    private ApplicationStatus applicationStatus;

    @ManyToOne
    private Candidate candidate;
    @Column
    private Interview interview;


    public Application(RequirementAnswer requirementAnswer, RequirementResult requirementResult, Set<ApplicationFile> files, Date applicationDate, Interview interview) {

        Preconditions.noneNull(requirementAnswer, requirementResult, files, applicationDate);

        this.requirementAnswer = requirementAnswer;
        this.requirementResult = requirementResult;
        this.files = files;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
        this.interview = interview;
    }

    public Application(RequirementAnswer requirementAnswer, RequirementResult requirementResult, Set<ApplicationFile> files, Date applicationDate, Candidate candidate) {

        Preconditions.noneNull(requirementAnswer, requirementResult, files, applicationDate, candidate);

        this.requirementAnswer = requirementAnswer;
        this.requirementResult = requirementResult;
        this.files = files;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
        this.candidate = candidate;
    }

    public Application(Set<ApplicationFile> files, Date applicationDate, Candidate candidate) {

        Preconditions.noneNull(files, applicationDate, candidate);

        this.files = files;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
        this.candidate = candidate;
    }

    public Application(Set<ApplicationFile> files, Date applicationDate) {

        Preconditions.noneNull(files, applicationDate);

        this.files = files;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
    }


    public Application(RequirementAnswer requirementAnswer, RequirementResult requirementResult, Set<ApplicationFile> files, Date applicationDate, Candidate candidate, Interview interview) {

        Preconditions.noneNull(requirementAnswer, requirementResult, files, applicationDate, candidate);

        this.requirementAnswer = requirementAnswer;
        this.requirementResult = requirementResult;
        this.files = files;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
        this.candidate = candidate;
        this.interview = interview;
    }

    public Application(String requirementAnswer, Boolean requirementResult, Set<ApplicationFile> files, Date applicationDate, Candidate candidate, Interview interview) {

        Preconditions.noneNull(requirementAnswer, requirementResult, files, applicationDate, candidate);

        this.requirementAnswer = RequirementAnswer.valueOf(requirementAnswer);
        this.requirementResult = RequirementResult.valueOf(requirementResult);
        this.files = files;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
        this.candidate = candidate;
        this.interview = interview;
    }

    public Application(RequirementAnswer requirementAnswer, RequirementResult requirementResult, Set<ApplicationFile> files, Date applicationDate) {

        Preconditions.noneNull(requirementAnswer, requirementResult, files, applicationDate);

        this.requirementAnswer = requirementAnswer;
        this.requirementResult = requirementResult;
        this.files = files;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
    }


    public Application(String requirementAnswer, Boolean requirementResult, Set<ApplicationFile> files, Date applicationDate) {
        Preconditions.noneNull(requirementAnswer, requirementResult, files, applicationDate);

        this.requirementAnswer = RequirementAnswer.valueOf(requirementAnswer);
        this.requirementResult = RequirementResult.valueOf(requirementResult);
        this.files = files;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
    }

    protected Application(){
        //for ORM
    }

    public Candidate getCandidate() {
        return candidate;
    }

    @Override
    public boolean sameAs(Object other){
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int compareTo(Long other){
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public Long identity() {
        return this.id;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (!(o instanceof Application)) return false;
        Application that = (Application) o;
        return Objects.equals(version, that.version) && Objects.equals(id, that.id) &&
                Objects.equals(requirementAnswer, that.requirementAnswer) && Objects.equals(requirementResult, that.requirementResult) && Objects.equals(files, that.files);
    }
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public ApplicationDTO toDTO() {
        return new ApplicationDTO(id, files, applicationDate,
                applicationStatus.getStatusDescription(),candidate.user().username().toString());
    }
}
