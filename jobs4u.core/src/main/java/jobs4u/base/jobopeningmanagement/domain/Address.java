package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address implements ValueObject {
    private String streetName;
    private String  city;
    private String district;
    private  String state;
    private int zipcode;

    public Address(String streetName, String city, String district, String state, int zipcode) {
        this.streetName = streetName;
        this.city = city;
        this.district = district;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Address() {

    }
}
