package jobs4u.base.notificationmanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.network.FollowUpConnectionService;
import jobs4u.base.notificationmanagement.dto.NotificationDTO;
import jobs4u.base.notificationmanagement.thread.CheckForNotificationsThread;
import org.apache.commons.lang3.tuple.Pair;

import java.util.NoSuchElementException;
import java.util.Optional;

public class NotificationInboxCustomerController {

    FollowUpConnectionService followUpConnectionService;
    AuthorizationService authorizationService;

    public NotificationInboxCustomerController() {
        this.followUpConnectionService = new FollowUpConnectionService();
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

    public Pair<Boolean, String> establishConnection(Username username, String password) {
        return followUpConnectionService.establishConnection(username, password);
    }

    public void startCheckForNotificationsThread(Username username){
        Thread thread = new Thread(new CheckForNotificationsThread(username));
        thread.start();
    }

    public Pair<Boolean, String> closeConnection() {
        return FollowUpConnectionService.closeConnection();
    }

    public Iterable<NotificationDTO> getAllUnseenNotifications(Username username) {
        return followUpConnectionService.receiveUnseenNotificationList(username.toString());
    }

    public Iterable<NotificationDTO> getAllSeenNotifications(Username username, String date) {
        return followUpConnectionService.receiveSeenNotificationList(username.toString(), date);
    }
}
