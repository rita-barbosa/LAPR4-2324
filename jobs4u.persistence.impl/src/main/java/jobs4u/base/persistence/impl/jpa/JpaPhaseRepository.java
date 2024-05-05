package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.Application;
import jobs4u.base.recruitmentprocessmanagement.domain.Phase;
import jobs4u.base.recruitmentprocessmanagement.repository.PhaseRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JpaPhaseRepository
        extends JpaAutoTxRepository<Phase, Long, Long>
        implements PhaseRepository {

    public JpaPhaseRepository(final TransactionalContext autoTx) {
        super(autoTx, "phaseId");
    }

    public JpaPhaseRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "phaseId");
    }

    @Override
    public Iterable<Phase> phases() {
        return findAll();
    }

    @Override
    public Optional<Phase> getPhaseById(long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("phaseId", id);
        return matchOne("e.requirementName.name=:filename", params);
    }


}
