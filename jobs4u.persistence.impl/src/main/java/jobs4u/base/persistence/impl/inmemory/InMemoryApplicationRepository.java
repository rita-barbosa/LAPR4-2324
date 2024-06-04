package jobs4u.base.persistence.impl.inmemory;


import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.customermanagement.domain.Customer;
import jobs4u.base.customermanagement.repository.CustomerRepository;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.domain.JobOpeningStatus;
import jobs4u.base.jobopeningmanagement.domain.JobOpeningStatusEnum;
import jobs4u.base.jobopeningmanagement.domain.JobReference;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InMemoryApplicationRepository
        extends InMemoryDomainRepository<Application, Long>
        implements ApplicationRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Application> applications() {
        List<Application> applicationList = new ArrayList<>();
        Iterable<Application> applications = findAll();
        for (Application app : applications) {
            applicationList.add(app);
        }
        return applicationList;
    }

    @Override
    public Iterable<Application> applicationsForJobOpeningWithRequirements(String jobReference) {
        JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
        Optional<JobOpening> jos = jobOpeningRepository.getJobOpeningByJobReference(new JobReference(jobReference));
        jos.ifPresent(jobOpening -> match(e -> e.requirementAnswerFilePath() != null && jobOpening.getApplications().contains(e)));
        return Collections.emptyList();
    }

    @Override
    public Iterable<Application> applicationsFromCandidate(String phoneNumber) {
        return match(e -> e.candidate().phoneNumber().number().equals(phoneNumber));
    }
}
