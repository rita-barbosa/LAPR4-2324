package jobs4u.base.rankmanagement.domain;


import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import jakarta.persistence.*;
import jobs4u.base.jobopeningmanagement.domain.JobReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_RANK")
public class Rank implements AggregateRoot<JobReference> {

    @EmbeddedId
    private JobReference jobReference;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RankOrder> entries = new ArrayList<>();

    public Rank() {
        //for ORM
    }

    public Rank(JobReference jobOpeningAssociated, List<RankOrder> rankOrders) {
        this.jobReference = jobOpeningAssociated;
        this.entries = rankOrders;
    }
    public Rank(JobReference jobOpeningAssociated) {
        this.jobReference = jobOpeningAssociated;
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public List<RankOrder> rankEntries() {
        return this.entries;
    }

    @Override
    public JobReference identity() {
        return this.jobReference;
    }
}
