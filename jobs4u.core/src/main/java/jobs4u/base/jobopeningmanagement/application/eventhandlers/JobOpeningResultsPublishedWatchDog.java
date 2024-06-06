package jobs4u.base.jobopeningmanagement.application.eventhandlers;

import eapli.framework.domain.events.DomainEvent;
import eapli.framework.infrastructure.pubsub.EventHandler;
import jobs4u.base.jobopeningmanagement.domain.events.ApplicationAcceptedEvent;
import jobs4u.base.jobopeningmanagement.domain.events.JobOpeningResultsPublishedEvent;

public class JobOpeningResultsPublishedWatchDog implements EventHandler {
    @Override
    public void onEvent(DomainEvent domainevent) {
        assert domainevent instanceof JobOpeningResultsPublishedEvent;

        final JobOpeningResultsPublishedEvent event = (JobOpeningResultsPublishedEvent) domainevent;

        final SendEmailOnJobOpeningResultsPublishedController
                controller = new SendEmailOnJobOpeningResultsPublishedController();
        controller.sendEmail(event);
    }
}
