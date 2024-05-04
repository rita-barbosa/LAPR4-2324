package jobs4u.base.languageenginnermanagement.interviewmodelmanagement.application;

import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.domain.InterviewModelName;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementName;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.repositories.RequirementSpecificationRepository;

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
