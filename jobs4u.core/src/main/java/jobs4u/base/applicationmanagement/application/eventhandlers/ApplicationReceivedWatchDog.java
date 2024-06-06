package jobs4u.base.applicationmanagement.application.eventhandlers;

import eapli.framework.domain.events.DomainEvent;
import eapli.framework.infrastructure.pubsub.EventHandler;
import jobs4u.base.jobopeningmanagement.domain.events.ApplicationReceivedEvent;

public class ApplicationReceivedWatchDog implements EventHandler {

    @Override
    public void onEvent(DomainEvent domainevent) {
        assert domainevent instanceof ApplicationReceivedEvent;

        final ApplicationReceivedEvent event = (ApplicationReceivedEvent) domainevent;

        final NotifyCandidateOnApplicationStatusChangedController
                controller = new NotifyCandidateOnApplicationStatusChangedController();
        controller.notifyCandidateOfReceived(event);
    }
}
