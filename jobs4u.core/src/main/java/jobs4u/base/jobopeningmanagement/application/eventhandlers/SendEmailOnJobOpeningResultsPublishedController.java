package jobs4u.base.jobopeningmanagement.application.eventhandlers;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.customermanagement.domain.Customer;
import jobs4u.base.customermanagement.repository.CustomerRepository;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.events.ApplicationAcceptedEvent;
import jobs4u.base.jobopeningmanagement.domain.events.JobOpeningResultsPublishedEvent;
import jobs4u.base.network.FollowUpConnectionService;
import org.apache.commons.lang3.tuple.Pair;

import java.util.NoSuchElementException;
import java.util.Optional;

public class SendEmailOnJobOpeningResultsPublishedController {
    private static final FollowUpConnectionService connectionService = new FollowUpConnectionService();
    private static final AuthorizationService authz = AuthzRegistry.authorizationService();
    private static final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    private Username getSessionCredentials() {
        Optional<UserSession> session = authz.session();
        if (session.isPresent()) {
            SystemUser user = session.get().authenticatedUser();
            return user.identity();
        }
        throw new NoSuchElementException("No session found");
    }

    private Pair<Boolean, String> establishConnection(Username username, String password) {
        return connectionService.establishConnection(username, password);
    }

    public void sendEmail(JobOpeningResultsPublishedEvent event) {
        try {
            Optional<Customer> customer = customerRepository.getCustomerByCustomerCode(event.companyCode());
            Username user = getSessionCredentials();
            Pair<Boolean, String> connection = establishConnection(user, event.userPassword());
            if (!connection.getKey()) {
                throw new IllegalArgumentException("Error: Could not establish connection" + connection.getValue());
            }
            connectionService.sendEmail(event.senderEmail(), customer.get().customerUser().email().toString(), event.topic(), event.information());

        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println("Error");
        }
    }
}
