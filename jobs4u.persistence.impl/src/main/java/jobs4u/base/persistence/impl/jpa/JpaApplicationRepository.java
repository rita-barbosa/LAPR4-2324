package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.candidatemanagement.domain.CandidateUser;
import jobs4u.base.candidatemanagement.domain.PhoneNumber;


import java.util.List;
import java.util.Map;
public class JpaApplicationRepository
    //defini o candidateUser como identity, mas dps se quiseres mudas
        extends JpaAutoTxRepository<Application, Long, CandidateUser>
        implements ApplicationRepository {

    public JpaApplicationRepository(final TransactionalContext autoTx) {
        super(autoTx, "candidate");
    }

    public JpaApplicationRepository(final String puname) {
        super(puname, jobs4u.base.Application.settings().getExtendedPersistenceProperties(), "candidate");
    }

    @Override
    public List<Application> applications() {
        return null;
    }
}
