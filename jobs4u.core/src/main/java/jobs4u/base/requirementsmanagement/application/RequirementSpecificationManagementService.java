package jobs4u.base.requirementsmanagement.application;

import jobs4u.base.infrastructure.persistence.PersistenceContext;

import jobs4u.base.requirementsmanagement.dto.RequirementSpecificationDTO;
import jobs4u.base.requirementsmanagement.repositories.RequirementSpecificationRepository;


public class RequirementSpecificationManagementService {

    private final RequirementSpecificationRepository repo = PersistenceContext
            .repositories().requirementSpecifications();

    private final RequirementSpecificationDTOService dtoSvc = new RequirementSpecificationDTOService();

    public Iterable<RequirementSpecificationDTO> allRequirementSpecifications() {
        return dtoSvc.convertToDTO(repo.findAll());
    }
}
