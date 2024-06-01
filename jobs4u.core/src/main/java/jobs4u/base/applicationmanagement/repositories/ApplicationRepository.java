package jobs4u.base.applicationmanagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.base.applicationmanagement.domain.Application;

import java.util.List;

public interface ApplicationRepository extends DomainRepository<Long, Application> {

    Iterable<Application> applications();

    Iterable<Application> applicationsForJobOpeningWithRequirements(String jobReference);
}
