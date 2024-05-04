package jobs4u.base.candidatemanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.candidatemanagement.domain.CandidateUser;
import jobs4u.base.candidatemanagement.domain.PhoneNumber;
import jobs4u.base.clientusermanagement.domain.ClientUser;
import jobs4u.base.clientusermanagement.domain.MecanographicNumber;

import java.util.Optional;

public interface CandidateRepository extends DomainRepository<PhoneNumber, CandidateUser> {
    Optional<CandidateUser> findByUsername(Username name);

    default Optional<CandidateUser> findByPhoneNumber(final PhoneNumber number) {
        return ofIdentity(number);
    }

}
