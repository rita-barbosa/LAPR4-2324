package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.util.Comparator;

@Embeddable
public class Address implements ValueObject {
    private String streetName;
    private String city;
    private String district;
    private String streetNumber;
    private String zipcode;

    public Address(String streetName, String city, String district, String streetNumber, String zipcode) {
        Preconditions.noneNull(streetName, city, district, streetNumber, zipcode);

        Preconditions.nonEmpty(streetName);
        Preconditions.nonEmpty(city);
        Preconditions.nonEmpty(district);
        Preconditions.nonEmpty(streetNumber);
        Preconditions.nonEmpty(zipcode);

        this.streetName = streetName;
        this.city = city;
        this.district = district;
        this.streetNumber = streetNumber;
        this.zipcode = zipcode;
    }

    public Address(String address) {
        String[] parts = address.split(",\\s*");
        if (parts.length >= 5) {
            this.streetName = parts[0];
            this.streetNumber = parts[1];
            this.city = parts[2];
            this.district = parts[3];
            this.zipcode = parts[4];

        } else {
            // Handle case where the address string does not contain all required parts
            // For example, throw an IllegalArgumentException
            throw new IllegalArgumentException("Invalid address format: " + address);
        }
    }

    protected Address() {

    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", streetName, streetNumber, city, district, zipcode);
    }
}
