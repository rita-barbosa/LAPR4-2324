package jobs4u.base.candidatemanagement;

import eapli.framework.general.domain.model.EmailAddress;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Candidate {

    @Id
    private EmailAddress Email;

    private CandidateName name;

    @Column(unique = true)
    private PhoneNumber phoneNumber;

    public Candidate() {
        //for ORM
    }
}
