package jobs4u.base.rankmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.EmailAddress;
import jakarta.persistence.*;

import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.candidatemanagement.domain.Candidate;
import jobs4u.base.jobopeningmanagement.domain.JobReference;

@Entity
@Table(name = "T_RANK_ENTRIES")
public class RankOrder implements ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer rankEntryId;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    private Integer numberRanked;

    protected RankOrder() {
        //for ORM
    }

    public RankOrder(Application application, Integer numberRanked) {
        this.application = application;
        this.numberRanked = numberRanked;
    }

    public Integer numberRanked() {
        return this.numberRanked;
    }

    public Application acceptApplication() {
        this.application.acceptApplication();
        return this.application;
    }

    public EmailAddress candidateEmail() {
        return this.application.candidate().email();
    }

    public Application rejectApplication() {
        this.application.rejectApplication();
        return this.application;
    }
}
