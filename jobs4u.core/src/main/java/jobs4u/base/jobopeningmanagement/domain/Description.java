package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Id;

@Embeddable
public class Description implements ValueObject {


    private String descriptionText;

    public Description(String text) {
        this.descriptionText = text;
    }

    public Description() {
        //for ORM
    }

    public static Description valueOf(final String text) {
        return new Description(text);
    }

}
