package jobs4u.base.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.jobopeningmanagement.domain.WorkMode;
import jobs4u.base.jobopeningmanagement.repositories.WorkModeRepository;

import java.util.List;

public class InMemoryWorkModeRepository extends InMemoryDomainRepository<WorkMode, String>
        implements WorkModeRepository {
    @Override
    public List<WorkMode> workModes() {
        return null;
    }
}
