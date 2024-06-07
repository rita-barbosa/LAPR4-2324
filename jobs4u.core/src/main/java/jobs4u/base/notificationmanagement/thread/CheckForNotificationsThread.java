package jobs4u.base.notificationmanagement.thread;

import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.time.util.Calendars;
import jobs4u.base.network.FollowUpConnectionService;
import jobs4u.base.network.SerializationUtil;
import jobs4u.base.network.data.DataDTO;
import jobs4u.base.notificationmanagement.dto.NotificationDTO;
import jobs4u.base.notificationmanagement.application.NotificationManagementService;

import java.io.DataOutputStream;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Calendar;

public class CheckForNotificationsThread implements Runnable {

    public final Username username;
    private final FollowUpConnectionService followUpConnectionService;

    public CheckForNotificationsThread(Username username) {
        this.username = username;
        this.followUpConnectionService = new FollowUpConnectionService();
    }

    @Override
    public void run() {
        String answer;

        while(!Thread.interrupted()) {

            answer = (String) SerializationUtil.deserialize(followUpConnectionService.checkForNotifications(username).dataBlockList().get(0).data());

            if(answer.equals("true")){
                System.out.println("\n[NEW NOTIFICATIONS TO BE SEEN]");
            }

            try {
                wait(30000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }


}
