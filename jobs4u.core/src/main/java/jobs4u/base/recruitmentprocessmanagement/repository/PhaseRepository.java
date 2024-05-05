package jobs4u.base.recruitmentprocessmanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.base.interviewmodelmanagement.domain.InterviewModel;
import jobs4u.base.recruitmentprocessmanagement.domain.Phase;
import jobs4u.base.recruitmentprocessmanagement.domain.RecruitmentProcess;

import java.util.List;
import java.util.Optional;

public interface PhaseRepository
        extends DomainRepository<Long, Phase> {

    Iterable<Phase> phases();

    Optional<Phase> getPhaseById(long id);
}
