package jobs4u.base.requirementsmanagement.application;

import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.requirementsmanagement.repositories.RequirementSpecificationRepository;

public class RequirementSpecificationManagementService {

    private final RequirementSpecificationRepository repo = PersistenceContext
            .repositories().requirementSpecifications();


    public Iterable<RequirementSpecification> availableRequirementSpecification() {
        return repo.requirementsSpecifications();
    }


}
