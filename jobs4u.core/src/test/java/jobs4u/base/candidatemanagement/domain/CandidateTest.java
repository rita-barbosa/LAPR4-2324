package jobs4u.base.candidatemanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.*;
import jobs4u.base.usermanagement.domain.BaseRoles;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidateTest {

     private final PhoneNumber phoneNumber1 = new PhoneNumber("+351","910000000");
    private final PhoneNumber phoneNumber2 = new PhoneNumber("+351","910000002");

    public static SystemUser dummyUser(final String username, final Role... roles) {
        // should we load from spring context?
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
    }

    private SystemUser getNewDummyUser() {
        return dummyUser("dummy", BaseRoles.OPERATOR);
    }

    @Test
    public void ensureEqualsCandidateUsersPassesForSamePhoneNumber() throws Exception {

        final Candidate candidate1 = new Candidate(getNewDummyUser(),phoneNumber1);
        final Candidate candidate2 = new Candidate(getNewDummyUser(),phoneNumber1);

        final boolean expected = candidate1.equals(candidate2);

        assertTrue(expected);
    }

    @Disabled
    @Test
    public void ensureEqualsCandidateUsersFailsForSamePhoneNumber() throws Exception {

        final Candidate candidate1 = new Candidate(getNewDummyUser(),phoneNumber1);
        final Candidate candidate2 = new Candidate(getNewDummyUser(),phoneNumber2);

        final boolean expected = candidate1.equals(candidate2);

        assertTrue(expected);
    }
/*
    @Test
    public void ensureClientUserEqualsFailsForDifferenteMecanographicNumber() throws Exception {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.ADMIN);

        final ClientUser aClientUser = new ClientUserBuilder().withMecanographicNumber(aMecanographicNumber)
                .withSystemUser(getNewDummyUser()).build();

        final ClientUser anotherClientUser = new ClientUserBuilder()
                .withMecanographicNumber(anotherMecanographicNumber).withSystemUser(getNewDummyUser()).build();

        final boolean expected = aClientUser.equals(anotherClientUser);

        assertFalse(expected);
    }

    @Test
    public void ensureClientUserEqualsAreTheSameForTheSameInstance() throws Exception {
        final ClientUser aClientUser = new ClientUser();

        final boolean expected = aClientUser.equals(aClientUser);

        assertTrue(expected);
    }

    @Test
    public void ensureClientUserEqualsFailsForDifferenteObjectTypes() throws Exception {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.ADMIN);

        final ClientUser aClientUser = new ClientUserBuilder().withMecanographicNumber("DUMMY")
                .withSystemUser(getNewDummyUser()).build();

        @SuppressWarnings("unlikely-arg-type")
        final boolean expected = aClientUser.equals(getNewDummyUser());

        assertFalse(expected);
    }

    @Test
    public void ensureClientUserIsTheSameAsItsInstance() throws Exception {
        final ClientUser aClientUser = new ClientUserBuilder().withMecanographicNumber("DUMMY")
                .withSystemUser(getNewDummyUser()).build();

        final boolean expected = aClientUser.sameAs(aClientUser);

        assertTrue(expected);
    }

    @Test
    public void ensureTwoClientUserWithDifferentMecanographicNumbersAreNotTheSame() throws Exception {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.ADMIN);
        final ClientUser aClientUser = new ClientUserBuilder().withMecanographicNumber(aMecanographicNumber)
                .withSystemUser(getNewDummyUser()).build();

        final ClientUser anotherClientUser = new ClientUserBuilder()
                .withMecanographicNumber(anotherMecanographicNumber).withSystemUser(getNewDummyUser()).build();

        final boolean expected = aClientUser.sameAs(anotherClientUser);

        assertFalse(expected);
    }
    */
}