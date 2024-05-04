package jobs4u.base.jobopeningmanagement.domain.rank;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.*;
import jobs4u.base.candidatemanagement.Candidate;
import jobs4u.base.candidatemanagement.domain.CandidateUser;

@Entity
@Table(name = "T_RANK_ENTRIES")
public class RankEntry implements ValueObject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer rankEntryId;

    @ManyToOne
    private CandidateUser candidate;

    private Integer numberRanked;

    protected RankEntry() {
        //for ORM
    }

    public RankEntry(CandidateUser candidate, Integer numberRanked) {
        this.candidate = candidate;
        this.numberRanked = numberRanked;
    }

    public Integer numberRanked(){
        return this.numberRanked;
    }

}
