package jobs4u.base.candidatemanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jobs4u.base.candidatemanagement.domain.Candidate;
import jobs4u.base.usermanagement.domain.BaseRoles;

import java.util.Optional;

public class EnableDisableCandidateController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final static CandidateManagementService candidateService = new CandidateManagementService();
    private final UserManagementService userService = AuthzRegistry.userService();

    public Iterable<Candidate> activeCandidates() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.OPERATOR);

        return candidateService.activeCandidates();
    }
    public Iterable<Candidate> deactivatedCandidates() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.OPERATOR);

        return candidateService.deactivatedCandidates();
    }

    public void deactivateCandidate(final String phoneNumber) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.OPERATOR);
        Candidate candidate= candidateService.getCandidateByPhoneNumber(phoneNumber).get();
        userService.deactivateUser(candidate.user());
    }
    public void activateCandidate(final String phoneNumber) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.OPERATOR);
        Candidate candidate= candidateService.getCandidateByPhoneNumber(phoneNumber).get();
        userService.activateUser(candidate.user());
    }
}
