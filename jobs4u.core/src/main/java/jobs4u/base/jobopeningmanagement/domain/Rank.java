package jobs4u.base.jobopeningmanagement.domain;


import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Embeddable
public class Rank implements ValueObject {

    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Rank")
    private Integer numberRanked;

    public Rank() {
        //for ORM
    }

    public Rank(Integer numberRanked) {
        this.numberRanked = numberRanked;
    }

    public Integer rank(){
        return this.numberRanked;
    }
}
