package jobs4u.base.entitymanagement.domain;

import jobs4u.base.jobopeningmanagement.domain.Address;
import jobs4u.base.usermanagement.domain.BaseRoles;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

import static org.junit.Assert.assertThrows;


public class CustomerCodeTest {
    @Test
    public void ensureDoesntHaveMoreThanTenCharacters() {

        assertThrows(IllegalArgumentException.class, () -> new CustomerCode("MORETHANTEN"));
    }

    @Test
    public void ensureMustNotBeNull() {
        assertThrows(IllegalArgumentException.class, () -> new CustomerCode(null));
    }


    @Test
    public void ensureMustNotBeEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new CustomerCode("    "));
    }

}