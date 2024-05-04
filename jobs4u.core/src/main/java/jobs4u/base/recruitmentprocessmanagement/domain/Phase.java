package jobs4u.base.recruitmentprocessmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

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

//    @ManyToOne
//    @JoinColumn
//    private RecruitmentProcess recruitmentprocess;

    protected Phase(){
        //for ORM
    }

    public Phase(PhaseType phaseType, PhaseDescription description, PhaseStatus status, PhasePeriod period/*,
                 RecruitmentProcess recruitmentprocess*/) {
        Preconditions.noneNull(phaseType, period, description, status/*, recruitmentprocess*/);
        this.phaseType = phaseType;
        this.description = description;
        this.status = status;
        this.period = period;
      //  this.recruitmentprocess = recruitmentprocess;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.phaseId;
    }


}
