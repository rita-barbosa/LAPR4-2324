package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.Application;
import jobs4u.base.candidatemanagement.domain.CandidateUser;
import jobs4u.base.candidatemanagement.domain.PhoneNumber;
import jobs4u.base.candidatemanagement.repository.CandidateRepository;
import jobs4u.base.customermanagement.domain.Customer;


import java.util.List;
import java.util.Optional;

public class JpaCandidateRepository extends JpaAutoTxRepository<CandidateUser, Long, PhoneNumber>
        implements CandidateRepository {

    public JpaCandidateRepository(final TransactionalContext autoTx) {
        super(autoTx, "phoneNumber");
    }

    public JpaCandidateRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "phoneNumber");
    }

    @Override
    public Optional<CandidateUser> ofIdentity(PhoneNumber entityPhoneNumber) {
        return Optional.empty();
    }

    @Override
    public void deleteOfIdentity(PhoneNumber entityPhoneNumber) {

    }

    @Override
    public Optional<CandidateUser> findByUsername(Username name) {
        return Optional.empty();
    }
}
