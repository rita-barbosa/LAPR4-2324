package jobs4u.base.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.recruitmentprocessmanagement.domain.Phase;
import jobs4u.base.recruitmentprocessmanagement.domain.RecruitmentProcess;
import jobs4u.base.recruitmentprocessmanagement.repository.PhaseRepository;
import jobs4u.base.recruitmentprocessmanagement.repository.RecruitmentProcessRepository;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryPhaseRepository
        extends InMemoryDomainRepository<Phase, Long>
        implements PhaseRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<Phase> phases() {
        List<Phase> phasesList = new ArrayList<>();
        Iterable<Phase> phases = findAll();
        for (Phase pha : phases) {
            phasesList.add(pha);
        }
        return phasesList;
    }

    @Override
    public Optional<Phase> getPhaseById(long id) {
        return matchOne(e -> e.identity().longValue() == id);
    }
}
