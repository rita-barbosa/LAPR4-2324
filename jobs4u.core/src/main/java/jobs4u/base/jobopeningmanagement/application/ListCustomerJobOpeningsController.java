package jobs4u.base.jobopeningmanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.network.ConnectionRelatedController;
import jobs4u.base.network.FollowUpConnectionService;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


public class ListCustomerJobOpeningsController implements ConnectionRelatedController {

    FollowUpConnectionService connectionService;
    AuthorizationService authorizationService;

    public ListCustomerJobOpeningsController() {
        this.connectionService = new FollowUpConnectionService();
        this.authorizationService = AuthzRegistry.authorizationService();
    }

    public Username getSessionCredentials() {
        Optional<UserSession> session = authorizationService.session();
        if (session.isPresent()) {
            SystemUser user = session.get().authenticatedUser();
            return user.identity();
        }
        throw new NoSuchElementException("No session found");
    }

    public Pair<Boolean, String> establishConnection(Username username, String password, int portNumber) {
       return connectionService.establishConnection(username, password, portNumber);
    }

    public List<JobOpeningDTO> jobOpeningsDataList(Username username){
        return connectionService.receiveJobOpeningsDataList(username);
    }

    public Pair<Boolean, String> closeConnection() {
        return FollowUpConnectionService.closeConnection();
    }
}
