package jobs4u.base.network;

import eapli.framework.infrastructure.authz.domain.model.Username;
import org.apache.commons.lang3.tuple.Pair;

public interface ConnectionRelatedController {

    Username getSessionCredentials();

    Pair<Boolean, String> establishConnection(Username username, String password, int portNumber);

    Pair<Boolean, String> closeConnection();

}
