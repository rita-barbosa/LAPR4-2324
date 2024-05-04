package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.Application;
import jobs4u.base.recruitmentprocessmanagement.domain.Phase;
import jobs4u.base.recruitmentprocessmanagement.repository.PhaseRepository;

public class JpaPhaseRepository
        extends JpaAutoTxRepository<Phase, Long, Long>
        implements PhaseRepository {

    public JpaPhaseRepository(final TransactionalContext autoTx) {
        super(autoTx, "phaseId");
    }

    public JpaPhaseRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "phaseId");
    }
}
