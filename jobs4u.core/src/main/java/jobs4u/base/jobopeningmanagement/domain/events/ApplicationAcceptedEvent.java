package jobs4u.base.jobopeningmanagement.domain.events;

import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.events.DomainEventBase;
import eapli.framework.general.domain.model.EmailAddress;
import jobs4u.base.jobopeningmanagement.domain.JobReference;

public class ApplicationAcceptedEvent extends DomainEventBase implements DomainEvent {
    private EmailAddress senderEmail;
    private EmailAddress receiverEmail;
    private String userPassword;
    private JobReference jobReference;

    public ApplicationAcceptedEvent(EmailAddress customerManagerEmail, EmailAddress candidateEmail, JobReference jobOpeningAssociated, String userPassword) {
        this.senderEmail = customerManagerEmail;
        this.receiverEmail = candidateEmail;
        this.jobReference = jobOpeningAssociated;
        this.userPassword = userPassword;
    }

    public String userPassword() {
        return userPassword;
    }

    public String senderEmail() {
        return senderEmail.toString();
    }

    public String receiverEmail() {
        return receiverEmail.toString();
    }

    public String topic() {
        return "Application Accepted";
    }

    public String information() {
        return "Hello, this is Jobs4u, we came to tell you that your application has been accepted for the job opening: " + jobReference.toString();
    }
}
