package jobs4u.base.rankmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.*;
import jobs4u.base.candidatemanagement.domain.Candidate;

@Entity
@Table(name = "T_RANK_ENTRIES")
public class RankOrder implements ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer rankEntryId;

    @ManyToOne
    private Candidate candidate;

    private Integer numberRanked;

    protected RankOrder() {
        //for ORM
    }

    public RankOrder(Candidate candidate, Integer numberRanked) {
        this.candidate = candidate;
        this.numberRanked = numberRanked;
    }

    public Integer numberRanked(){
        return this.numberRanked;
    }

}
