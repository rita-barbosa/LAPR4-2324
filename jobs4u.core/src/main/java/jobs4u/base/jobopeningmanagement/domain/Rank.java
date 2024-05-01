package jobs4u.base.jobopeningmanagement.domain;


import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class Rank implements ValueObject {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer numberRanked;
}
