package jobs4u.base.applicationmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.dto.DTOable;
import jakarta.persistence.*;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.candidatemanagement.Candidate;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;

import java.io.File;
import java.util.Date;

@Entity
@Table(name = "T_APPLICATION")
public class Application implements DTOable<ApplicationDTO>, AggregateRoot<Application> {
    private static final long serialVersionUID = 1L;
    @Version
    private Long version;

    @Column(nullable = false)
    private RequirementAnswer requirementAnswer;
    @Column(nullable = false)
    private RequirementResult requirementResult;
    @Column(nullable = false)
    private File file;
    @Column(nullable = false)
    private Date applicationDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus applicationStatus;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Candidate candidate;
    @Column(nullable = false)
    private Interview interview;




    public Application(String requirementAnswer, Integer requirementResult, File file, String email, Date applicationDate, Boolean applicationStatus, String candidateName, Integer phoneNumber, Interview interview) {
        this.requirementAnswer = requirementAnswer;
        this.requirementResult = requirementResult;
        this.file = file;
        this.applicationDate = applicationDate;
        this.applicationStatus = applicationStatus;
        this.interview = interview;
    }

    protected Application(){
        //for ORM
    }


    @Override
    public boolean sameAs(Object other){
        return false;
    }

    @Override
    public int compareTo(Application other){
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public Application identity() {
        return null;
    }

    @Override
    public ApplicationDTO toDTO() {
        return null;
    }
}
