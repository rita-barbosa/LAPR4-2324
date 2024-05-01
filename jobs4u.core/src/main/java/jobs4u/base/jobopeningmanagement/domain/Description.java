package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;

public class Description implements ValueObject {


    private final String descriptionText;

    public Description(String text) {
        this.descriptionText = text;
    }

    public static Description valueOf(final String text) {
        return new Description(text);
    }

}
