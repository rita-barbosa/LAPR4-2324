package jobs4u.base.candidatemanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.candidatemanagement.domain.Candidate;
import jobs4u.base.candidatemanagement.domain.PhoneNumber;

import java.util.Optional;

public interface CandidateRepository extends DomainRepository<PhoneNumber, Candidate> {
    Optional<Candidate> findByUsername(Username name);

    default Optional<Candidate> findByPhoneNumber(final PhoneNumber number) {
        return ofIdentity(number);
    }

}
