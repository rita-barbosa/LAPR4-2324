package jobs4u.base.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.recruitmentprocessmanagement.domain.Phase;
import jobs4u.base.recruitmentprocessmanagement.domain.RecruitmentProcess;
import jobs4u.base.recruitmentprocessmanagement.repository.PhaseRepository;
import jobs4u.base.recruitmentprocessmanagement.repository.RecruitmentProcessRepository;

public class InMemoryPhaseRepository
        extends InMemoryDomainRepository<Phase, Long>
        implements PhaseRepository {

    static {
        InMemoryInitializer.init();
    }
}
