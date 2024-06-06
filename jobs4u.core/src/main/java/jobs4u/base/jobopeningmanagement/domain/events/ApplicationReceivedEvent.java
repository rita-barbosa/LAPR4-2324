package jobs4u.base.jobopeningmanagement.domain.events;

import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.events.DomainEventBase;
import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.base.jobopeningmanagement.domain.JobReference;

public class ApplicationReceivedEvent extends DomainEventBase implements DomainEvent {

    private String emailAddress;
    private String jobReference;

    public ApplicationReceivedEvent(String emailAddress, String jobReference) {
        this.emailAddress = emailAddress;
        this.jobReference = jobReference;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getJobReference() {
        return jobReference;
    }

}
