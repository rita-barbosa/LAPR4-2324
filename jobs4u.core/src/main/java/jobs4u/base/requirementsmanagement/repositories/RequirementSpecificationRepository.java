package jobs4u.base.requirementsmanagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.domain.JobReference;
import jobs4u.base.requirementsmanagement.domain.RequirementName;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;

import java.util.List;
import java.util.Optional;

public interface RequirementSpecificationRepository
        extends DomainRepository<RequirementName, RequirementSpecification> {

    Iterable<RequirementSpecification> requirementsSpecifications();

    Optional<RequirementSpecification> getFileByName(String filename);
}
