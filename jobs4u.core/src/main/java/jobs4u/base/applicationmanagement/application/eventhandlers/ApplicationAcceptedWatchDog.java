package jobs4u.base.applicationmanagement.application.eventhandlers;

import eapli.framework.domain.events.DomainEvent;
import eapli.framework.infrastructure.pubsub.EventHandler;
import jobs4u.base.jobopeningmanagement.domain.events.ApplicationAcceptedEvent;


public class ApplicationAcceptedWatchDog implements EventHandler {
    @Override
    public void onEvent(DomainEvent domainevent) {
        assert domainevent instanceof ApplicationAcceptedEvent;

        final ApplicationAcceptedEvent event = (ApplicationAcceptedEvent) domainevent;

        final SendEmailOnApplicationAcceptedController
                controller = new SendEmailOnApplicationAcceptedController();
        controller.sendEmail(event);
    }
}
