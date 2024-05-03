package jobs4u.base.jobopeningmanagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.domain.JobReference;

public interface JobOpeningRepository extends DomainRepository<JobReference, JobOpening> {

    JobReference lastJobReference(String customerCode);

    public Iterable<JobOpening> findAllJobOpeningsNotStarted();
}
