package jobs4u.base.applicationmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.candidatemanagement.domain.Candidate;

import java.io.File;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "T_APPLICATION")
public class Application implements AggregateRoot<Long>, DTOable<ApplicationDTO> {
    private static final long serialVersionUID = 1L;
    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private RequirementAnswer requirementAnswer;
    @Column(nullable = false)
    private RequirementResult requirementResult;

    @Column(nullable = false)
    private File file;
    @Column(nullable = false)
    private Date applicationDate;
    @Column(nullable = false)
    private ApplicationStatus applicationStatus;

    @ManyToOne
//    @JoinColumn(nullable = false)
    private Candidate candidate;
    @Column
    private Interview interview;



//    public Application(RequirementAnswer requirementAnswer, RequirementResult requirementResult, File file, Date applicationDate, ApplicationStatus applicationStatus, Candidate candidate, Interview interview) {
//
//        Preconditions.noneNull(requirementAnswer, requirementAnswer, file, applicationDate, applicationStatus, candidate, interview);
//
//        this.requirementAnswer = requirementAnswer;
//        this.requirementResult = requirementResult;
//        this.file = file;
//        this.applicationDate = applicationDate;
//        this.applicationStatus = applicationStatus;
//        this.candidate = candidate;
//        this.interview = interview;
//    }
//
//    public Application(RequirementAnswer requirementAnswer, RequirementResult requirementResult, File file, Date applicationDate, Candidate candidate, Interview interview) {
//
//        Preconditions.noneNull(requirementAnswer, requirementResult, file, applicationDate, candidate);
//
//        this.requirementAnswer = requirementAnswer;
//        this.requirementResult = requirementResult;
//        this.file = file;
//        this.applicationDate = applicationDate;
//        this.applicationStatus = new ApplicationStatus();
//        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
//        this.candidate = candidate;
//        this.interview = interview;
//    }


    public Application(RequirementAnswer requirementAnswer, RequirementResult requirementResult, File file, Date applicationDate, Interview interview) {

        Preconditions.noneNull(requirementAnswer, requirementResult, file, applicationDate);

        this.requirementAnswer = requirementAnswer;
        this.requirementResult = requirementResult;
        this.file = file;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
        this.interview = interview;
    }

//    public Application(RequirementAnswer requirementAnswer, RequirementResult requirementResult, File file, Date applicationDate, ApplicationStatus applicationStatus, Candidate candidate, String interviewTypeDenomination, Date schedule, InterviewResult interviewResult, String interviewAnswer) {
//
//        Preconditions.noneNull(requirementAnswer, requirementResult, file, applicationDate, applicationStatus, candidate);
//
//        this.requirementAnswer = requirementAnswer;
//        this.requirementResult = requirementResult;
//        this.file = file;
//        this.applicationDate = applicationDate;
//        this.applicationStatus = applicationStatus;
//        this.candidate = candidate;
//        this.interview = new Interview(interviewTypeDenomination, schedule, interviewResult, interviewAnswer);
//    }
//
    public Application(RequirementAnswer requirementAnswer, RequirementResult requirementResult, File file, Date applicationDate, Candidate candidate, Interview interview) {

        Preconditions.noneNull(requirementAnswer, requirementResult, file, applicationDate, candidate);

        this.requirementAnswer = requirementAnswer;
        this.requirementResult = requirementResult;
        this.file = file;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
        this.candidate = candidate;
        this.interview = interview;
    }

    public Application(String requirementAnswer, Boolean requirementResult, File file, Date applicationDate, Candidate candidate, Interview interview) {

        Preconditions.noneNull(requirementAnswer, requirementResult, file, applicationDate, candidate);

        this.requirementAnswer = RequirementAnswer.valueOf(requirementAnswer);
        this.requirementResult = RequirementResult.valueOf(requirementResult);
        this.file = file;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
        this.candidate = candidate;
        this.interview = interview;
    }

    public Application(RequirementAnswer requirementAnswer, RequirementResult requirementResult, File file, Date applicationDate) {

        Preconditions.noneNull(requirementAnswer, requirementResult, file, applicationDate);

        this.requirementAnswer = requirementAnswer;
        this.requirementResult = requirementResult;
        this.file = file;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();
    }


    public Application(String requirementAnswer, Boolean requirementResult, File file, Date applicationDate, String candidateName) {
        Preconditions.noneNull(requirementAnswer, requirementResult, file, applicationDate);

        this.requirementAnswer = RequirementAnswer.valueOf(requirementAnswer);
        this.requirementResult = RequirementResult.valueOf(requirementResult);
        this.file = file;
        this.applicationDate = applicationDate;
        this.applicationStatus = new ApplicationStatus();
        this.applicationStatus.setStatusDescriptionAsNOT_CHECKED();

    }

    protected Application(){
        //for ORM
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
                Objects.equals(requirementAnswer, that.requirementAnswer) && Objects.equals(requirementResult, that.requirementResult);
    }
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public ApplicationDTO toDTO() {
        return new ApplicationDTO(id, requirementAnswer.requirementAnswer(), requirementResult.requirementResult(),
                file, applicationDate, applicationStatus.getStatusDescription());
    }
}
