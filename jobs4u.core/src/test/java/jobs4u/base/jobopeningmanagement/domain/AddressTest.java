package jobs4u.base.jobopeningmanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class AddressTest {

    String streetName = "StreetName";
    String city = "City";
    String district = "District";
    String streetNumber = "14th";
    String zipcode = "12345";


    @Test
    public void ensureZipcodeSpecificationsAreComplied() {
        assertThrows(IllegalArgumentException.class, () -> new Address(streetName, city, district, streetNumber, "123"));
        assertThrows(IllegalArgumentException.class, () -> new Address(streetName, city, district, streetNumber, "123456"));
        assertThrows(IllegalArgumentException.class, () -> new Address(streetName, city, district, streetNumber, "asdfg"));
    }


    @Test
    public void ensureAddressCannotHaveNull() {
        assertThrows(IllegalArgumentException.class, () -> new Address(null, city, district, streetNumber, zipcode));
        assertThrows(IllegalArgumentException.class, () -> new Address(streetName, null, district, streetNumber, zipcode));
        assertThrows(IllegalArgumentException.class, () -> new Address(streetName, city, null , streetNumber, zipcode));
        assertThrows(IllegalArgumentException.class, () -> new Address(streetName, city, district, null, zipcode));
        assertThrows(IllegalArgumentException.class, () -> new Address(streetName, city, district, streetNumber, null));
    }

    @Test
    public void ensureAddressCannotHaveEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Address("", city, district, streetNumber, zipcode));
        assertThrows(IllegalArgumentException.class, () -> new Address(streetName, "", district, streetNumber, zipcode));
        assertThrows(IllegalArgumentException.class, () -> new Address(streetName, city, "", streetNumber, zipcode));
        assertThrows(IllegalArgumentException.class, () -> new Address(streetName, city, district, "", zipcode));
        assertThrows(IllegalArgumentException.class, () -> new Address(streetName, city, district, streetNumber, ""));
    }


}