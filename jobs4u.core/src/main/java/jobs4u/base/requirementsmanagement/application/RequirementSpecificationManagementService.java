package jobs4u.base.requirementsmanagement.application;

import jobs4u.base.infrastructure.persistence.PersistenceContext;

import jobs4u.base.requirementsmanagement.domain.RequirementName;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.requirementsmanagement.dto.RequirementSpecificationDTO;
import jobs4u.base.requirementsmanagement.repositories.RequirementSpecificationRepository;


public class RequirementSpecificationManagementService {

    private static final RequirementSpecificationRepository repo = PersistenceContext
            .repositories().requirementSpecifications();

    private final RequirementSpecificationDTOService dtoSvc = new RequirementSpecificationDTOService();

    public Iterable<RequirementSpecificationDTO> allRequirementSpecifications() {
        return dtoSvc.convertToDTO(repo.findAll());
    }
    public static void registerRequirementPlugin(String nameOfRequirementPlugin, String descriptionOfRequirementPlugin, String fullClassName,String configFile, String dataImporter){
        RequirementSpecification requirementSpecification = new RequirementSpecification(nameOfRequirementPlugin, descriptionOfRequirementPlugin, fullClassName, configFile,dataImporter);
        repo.save(requirementSpecification);
    }

    public boolean checkIfRequirementSpecificationAlreadyExists(String identifier){
        return repo.containsOfIdentity(new RequirementName(identifier));
    }
}
