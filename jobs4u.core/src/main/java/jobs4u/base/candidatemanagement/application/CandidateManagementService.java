package jobs4u.base.candidatemanagement.application;

import eapli.framework.domain.events.DomainEvent;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.pubsub.EventPublisher;
import eapli.framework.infrastructure.pubsub.impl.inprocess.service.InProcessPubSub;
import jobs4u.base.candidatemanagement.domain.CandidateUser;
import jobs4u.base.candidatemanagement.domain.PhoneNumber;
import jobs4u.base.candidatemanagement.domain.events.NewCandidateUserRegisteredEvent;
import jobs4u.base.candidatemanagement.repository.CandidateRepository;
import jobs4u.base.customermanagement.domain.Customer;
import jobs4u.base.customermanagement.dto.CustomerDTO;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.usermanagement.application.GeneratePasswordService;
import jobs4u.base.usermanagement.domain.BaseRoles;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CandidateManagementService {
    private final GeneratePasswordService passwordService = new GeneratePasswordService();
    private final CandidateRepository candidateRepository= PersistenceContext.repositories().candidates();
    private final UserManagementService userManagementService = AuthzRegistry.userService();

    private final EventPublisher dispatcher = InProcessPubSub.publisher();

    public void registerCandidate(String name, String email, PhoneNumber phoneNumber) {
        String password = passwordService.generatePassword();

        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CANDIDATE_USER);

        SystemUser sysUser = userManagementService.registerNewUser(email, password, name,"Candidate",email, roles);

        final DomainEvent event = new NewCandidateUserRegisteredEvent(sysUser,phoneNumber);
        dispatcher.publish(event);
    }

    public List<CandidateUser> getCandidatesList() {
        Iterable<CandidateUser> candidatesList = candidateRepository.findAll();
        //Transformar Iterable em List
        List<CandidateUser> candidatesListOrdered = StreamSupport.stream(candidatesList.spliterator(),false).collect(Collectors.toList());

        candidatesListOrdered.sort(Comparator.comparing(CandidateUser::email));
        return candidatesListOrdered;
    }
}