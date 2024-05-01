package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.Application;
import jobs4u.base.jobopeningmanagement.domain.WorkMode;
import jobs4u.base.jobopeningmanagement.repositories.WorkModeRepository;

import java.util.List;

public class JpaWorkModeRepository
        extends JpaAutoTxRepository<WorkMode, String, String>
        implements WorkModeRepository {


    public JpaWorkModeRepository(final TransactionalContext autoTx) {
        super(autoTx, "workModeDesignation");
    }

    public JpaWorkModeRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "workModeDesignation");
    }

    @Override
    public List<WorkMode> workModes() {
        return null;
    }
}
