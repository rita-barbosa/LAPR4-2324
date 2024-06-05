package jobs4u.base.jobopeningmanagement.application.eventhandlers;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import jobs4u.base.customermanagement.domain.events.NewCustomerUserRegisteredEvent;
import jobs4u.base.jobopeningmanagement.domain.events.ApplicationAcceptedEvent;
import jobs4u.base.network.FollowUpConnectionService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.authz.application.UserSession;
import org.apache.commons.lang3.tuple.Pair;

import java.util.NoSuchElementException;
import java.util.Optional;

public class SendEmailOnApplicationAcceptedController {
    private static final FollowUpConnectionService connectionService = new FollowUpConnectionService();
    private static final AuthorizationService authz = AuthzRegistry.authorizationService();
    private static final int PORT_NUMBER = 6666;

    private Username getSessionCredentials() {
        Optional<UserSession> session = authz.session();
        if (session.isPresent()) {
            SystemUser user = session.get().authenticatedUser();
            return user.identity();
        }
        throw new NoSuchElementException("No session found");
    }

    private Pair<Boolean, String> establishConnection(Username username, String password, int portNumber) {
        return connectionService.establishConnection(username, password, portNumber);
    }

    public void sendEmail(ApplicationAcceptedEvent event) {
        try {
            Username user = getSessionCredentials();
            Pair<Boolean, String> connection = establishConnection(user, event.userPassword(), PORT_NUMBER);
            if (!connection.getKey()) {
                throw new IllegalArgumentException("Error: Could not establish connection" + connection.getValue());
            }
            connectionService.sendEmail(event.senderEmail(),event.receiverEmail(),event.topic(),event.information());

        } catch (NoSuchElementException | IllegalArgumentException e) {
            System.out.println("Error");
        }
    }
}
