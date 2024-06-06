package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.Application;
import jobs4u.base.jobopeningmanagement.domain.JobReference;
import jobs4u.base.rankmanagement.domain.Rank;
import jobs4u.base.rankmanagement.persistence.RankRepository;

import java.util.Map;

public class JpaRankRepository
        extends JpaAutoTxRepository<Rank, JobReference, JobReference>
        implements RankRepository {

    public JpaRankRepository(final TransactionalContext autoTx) {
        super(autoTx, "jobReference");
    }

    public JpaRankRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "jobReference");
    }
}
