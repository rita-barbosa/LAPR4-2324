package jobs4u.base.candidatemanagement.application;

import eapli.framework.domain.events.DomainEvent;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.pubsub.EventPublisher;
import eapli.framework.infrastructure.pubsub.impl.inprocess.service.InProcessPubSub;
import eapli.framework.validations.Preconditions;
import jobs4u.base.candidatemanagement.domain.CandidateUser;
import jobs4u.base.candidatemanagement.domain.PhoneNumber;
import jobs4u.base.candidatemanagement.domain.events.NewCandidateUserRegisteredEvent;
import jobs4u.base.candidatemanagement.dto.CandidateDTO;
import jobs4u.base.candidatemanagement.repository.CandidateRepository;
import jobs4u.base.customermanagement.domain.Customer;
import jobs4u.base.customermanagement.dto.CustomerDTO;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.usermanagement.application.GeneratePasswordService;
import jobs4u.base.usermanagement.domain.BaseRoles;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ListCandidatesDTOService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public List<CandidateDTO> toDTO(List<CandidateUser> listToDisplay){
        Preconditions.noneNull(listToDisplay);
        Preconditions.nonEmpty(listToDisplay);

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.OPERATOR);

        List<CandidateDTO> dtoList = new ArrayList<>();
        for (CandidateUser candidate : listToDisplay){
            dtoList.add(candidate.toDTO());
        }
        return dtoList;
    }


}
