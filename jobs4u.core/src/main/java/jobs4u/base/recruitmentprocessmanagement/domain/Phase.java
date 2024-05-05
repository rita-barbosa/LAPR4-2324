package jobs4u.base.recruitmentprocessmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.time.domain.model.DateInterval;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

import java.util.Calendar;

@Entity
@Table(name = "T_PHASE")
public class Phase implements AggregateRoot<Long>, ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phaseId;

    private PhaseType phaseType;

    private PhaseDescription description;

    private PhaseStatus status;

    private PhasePeriod period;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn
    private RecruitmentProcess recruitmentprocess;

    protected Phase(){
        //for ORM
    }

    public Phase(PhaseType phaseType, PhaseDescription description, PhaseStatus status, PhasePeriod period) {
        Preconditions.noneNull(phaseType, period, description, status);
        this.phaseType = phaseType;
        this.description = description;
        this.status = status;
        this.period = period;
    }

    public Phase(String phaseType, String description, String status, Calendar initial, Calendar end) {
        Preconditions.noneNull(phaseType, description, status, initial, end);
        Preconditions.ensure(initial.before(end));
        this.phaseType = new PhaseType(phaseType);
        this.description = new PhaseDescription(description);
        this.status = new PhaseStatus(status);
        this.period = new PhasePeriod(new DateInterval(initial,end));

    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.phaseId;
    }

    public void setRecruitmentprocess(RecruitmentProcess recruitmentprocess) {
        this.recruitmentprocess = recruitmentprocess;
    }
}
