package jobs4u.base.jobopeningmanagement.domain.rank;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.*;
import jobs4u.base.candidatemanagement.Candidate;

@Entity
@Table(name = "T_RANK_ENTRIES")
public class RankEntry implements ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer rankEntryId;

    @ManyToOne
    private Candidate candidate;

    private Integer numberRanked;

    protected RankEntry() {
        //for ORM
    }

    public RankEntry(Candidate candidate, Integer numberRanked) {
        this.candidate = candidate;
        this.numberRanked = numberRanked;
    }

    public Integer numberRanked(){
        return this.numberRanked;
    }

}
