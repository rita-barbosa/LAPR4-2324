package jobs4u.base.candidatemanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jobs4u.base.candidatemanagement.domain.CandidateUser;
import jobs4u.base.candidatemanagement.domain.PhoneNumber;
import jobs4u.base.candidatemanagement.dto.CandidateDTO;
import jobs4u.base.usermanagement.domain.BaseRoles;

import java.util.List;
import java.util.Optional;

public class ListCandidatesController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CandidateManagementService candidateManagementService = new CandidateManagementService();
    private final ListCandidatesDTOService dtoService = new ListCandidatesDTOService();


    public List<CandidateDTO> getCandidatesList(){
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.OPERATOR);

        List<CandidateUser> candidatesList =candidateManagementService.getCandidatesList();

        return dtoService.toDTO(candidatesList);
    }
}
