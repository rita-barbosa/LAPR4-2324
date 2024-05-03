package jobs4u.base.jobopeningmanagement.domain.rank;


import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import jakarta.persistence.*;
import jobs4u.base.jobopeningmanagement.domain.JobReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "T_RANK")
public class Rank implements AggregateRoot<JobReference> {

    @Id
    private JobReference jobOpeningAssociated;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<RankEntry> entries = new ArrayList<>();

    public Rank() {
        //for ORM
    }

    public Rank(JobReference jobOpeningAssociated) {
        this.jobOpeningAssociated = jobOpeningAssociated;
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public List<RankEntry> rankEntries(){
        return this.entries;
    }

    @Override
    public JobReference identity() {
        return this.jobOpeningAssociated;
    }
}
