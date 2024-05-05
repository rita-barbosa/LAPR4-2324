package jobs4u.base.interviewmodelmanagement.application;

import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.requirementsmanagement.domain.RequirementName;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.requirementsmanagement.repositories.RequirementSpecificationRepository;

public class RequirementSpecificationManagementService {

    private static final RequirementSpecificationRepository requirementSpecificationRepository = PersistenceContext.repositories().requirementSpecifications();

    public static void registerRequirementPlugin(String nameOfRequirementPlugin, String descriptionOfRequirementPlugin, String fullClassName){
        RequirementSpecification requirementSpecification = new RequirementSpecification(nameOfRequirementPlugin, descriptionOfRequirementPlugin, fullClassName);
        requirementSpecificationRepository.save(requirementSpecification);
    }

    public boolean checkIfRequirementSpecificationAlreadyExists(String identifier){
        return requirementSpecificationRepository.containsOfIdentity(new RequirementName(identifier));
    }

}
