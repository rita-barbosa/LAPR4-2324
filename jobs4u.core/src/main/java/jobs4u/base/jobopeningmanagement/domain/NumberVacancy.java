package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class NumberVacancy implements ValueObject {

    private int numVacancies;

    public NumberVacancy(Integer num) {
        this.numVacancies = num;
    }

    public NumberVacancy() {
        //for ORM
    }

    public static NumberVacancy valueOf(final Integer num) {
        return new NumberVacancy(num);
    }


}
