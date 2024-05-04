package jobs4u.base.recruitmentprocessmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;

import java.util.List;

@Entity
@Table(name = "T_RECRUITMENTPROCESS")
public class RecruitmentProcess implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruitmentID;

    private RecruitmentPeriod recruitmentPeriod;

    @OneToOne
    private JobOpening jobOpening;

    @OneToMany(/*mappedBy = "recruitmentprocess",*/ cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Phase> phases;

    public RecruitmentProcess(RecruitmentPeriod recruitmentPeriod, JobOpening jobOpening, List<Phase> phases) {
        Preconditions.noneNull(recruitmentPeriod, jobOpening, phases);
        this.recruitmentPeriod = recruitmentPeriod;
        this.jobOpening = jobOpening;
        this.phases = phases;
    }

    protected RecruitmentProcess() {
        //for ORM
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.recruitmentID;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public RecruitmentPeriod getRecruitmentPeriod() {
        return recruitmentPeriod;
    }
}
