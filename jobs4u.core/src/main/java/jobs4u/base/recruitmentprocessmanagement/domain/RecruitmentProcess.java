package jobs4u.base.recruitmentprocessmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.time.domain.model.DateInterval;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "T_RECRUITMENTPROCESS")
public class RecruitmentProcess implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruitmentID;

    private RecruitmentPeriod recruitmentPeriod;

    @OneToMany
    private List<Phase> phases;

    @OneToOne
    private JobOpening jobOpening;

    public RecruitmentProcess(Calendar initialDate, Calendar finalDate, List<Phase> phases) {
        Preconditions.noneNull(initialDate,finalDate, phases);
        Preconditions.ensure(initialDate.before(finalDate));
        this.recruitmentPeriod = new RecruitmentPeriod(new DateInterval(initialDate, finalDate));
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

    public List<Phase> allPhases(){
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public RecruitmentPeriod getRecruitmentPeriod() {
        return recruitmentPeriod;
    }
}
