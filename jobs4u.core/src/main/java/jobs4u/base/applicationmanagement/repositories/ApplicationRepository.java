package jobs4u.base.applicationmanagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;

public interface ApplicationRepository extends DomainRepository<Long, Application> {

    Iterable<Application> applications();

    Iterable<Application> applicationsForJobOpeningWithRequirements(String jobReference);

    Iterable<Application> applicationsFromCandidate(String phoneNumber);

    Application getApplicationFromDTO(ApplicationDTO applicationDTO);

    Iterable<Application> applicationsForJobOpeningWithInterviewAnswers(String jobReference);
}
