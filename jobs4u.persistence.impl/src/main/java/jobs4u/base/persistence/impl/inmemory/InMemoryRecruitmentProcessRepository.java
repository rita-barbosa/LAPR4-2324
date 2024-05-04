package jobs4u.base.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.recruitmentprocessmanagement.domain.RecruitmentProcess;
import jobs4u.base.recruitmentprocessmanagement.repository.RecruitmentProcessRepository;

public class InMemoryRecruitmentProcessRepository
        extends InMemoryDomainRepository<RecruitmentProcess, Long>
        implements RecruitmentProcessRepository {

    static {
        InMemoryInitializer.init();
    }
}
