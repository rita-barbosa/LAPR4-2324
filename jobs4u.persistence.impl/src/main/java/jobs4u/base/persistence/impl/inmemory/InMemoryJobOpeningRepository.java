package jobs4u.base.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.domain.JobOpeningStatus;
import jobs4u.base.jobopeningmanagement.domain.JobReference;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;

import java.util.NoSuchElementException;

public class InMemoryJobOpeningRepository
        extends InMemoryDomainRepository<JobOpening, JobReference>
        implements JobOpeningRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public JobReference lastJobReference(String customerCode) {
        int repoSize = (int) size();
        JobReference lastJobReference = null;
        if (repoSize == 0) {
            System.out.println("First job opening being registered in the system!");
            return new JobReference(customerCode, 0);
        }
        Iterable<JobOpening> jobOpenings = findAll();
        for (JobOpening element : jobOpenings) {
            lastJobReference = element.identity();
        }
        if (lastJobReference == null) {
            throw new NoSuchElementException("It was not possible to retrieve the last registered job opening.");
        }
        return lastJobReference;
    }

    @Override
    public Iterable<JobOpening> findAllJobOpeningsNotStarted() {
        return match(e -> e.jobOpeningStatus() == JobOpeningStatus.UNFINISHED || e.jobOpeningStatus() == JobOpeningStatus.NOT_STARTED);
    }
}
