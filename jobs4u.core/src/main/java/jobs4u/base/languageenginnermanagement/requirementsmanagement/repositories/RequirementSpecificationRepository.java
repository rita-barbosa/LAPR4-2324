package jobs4u.base.languageenginnermanagement.requirementsmanagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementName;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementSpecification;

import java.util.List;
import java.util.Optional;

public interface RequirementSpecificationRepository
        extends DomainRepository<RequirementName, RequirementSpecification> {

    Iterable<RequirementSpecification> requirementsSpecifications();

    Optional<RequirementSpecification> getFileByName(String filename);
}
