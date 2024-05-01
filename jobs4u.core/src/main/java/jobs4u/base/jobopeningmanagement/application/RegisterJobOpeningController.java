package jobs4u.base.jobopeningmanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.entitymanagement.dto.CustomerDTO;
import jobs4u.base.entitymanagement.application.CustomerManagementService;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.ContractType;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.domain.WorkMode;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;
import jobs4u.base.jobopeningmanagement.repositories.ContractTypeRepository;
import jobs4u.base.jobopeningmanagement.repositories.WorkModeRepository;
import jobs4u.base.requirementsmanagement.dto.RequirementSpecificationDTO;
import jobs4u.base.requirementsmanagement.repositories.RequirementSpecificationRepository;
import jobs4u.base.usermanagement.domain.BaseRoles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RegisterJobOpeningController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    CustomerManagementService customerManagementService = new CustomerManagementService();
    JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService();

    private final ContractTypeRepository contractTypeRepository = PersistenceContext
            .repositories().contractTypes();

    private final WorkModeRepository workModeRepository = PersistenceContext
            .repositories().workModes();

    private final RequirementSpecificationRepository requirementSpecificationRepository = PersistenceContext
            .repositories().requirementSpecifications();

    public List<CustomerDTO> getCustomersList() {
        Optional<SystemUser> user = authz.loggedinUserWithPermissions(BaseRoles.CUSTOMER_MANAGER);
        if (user.isPresent()) {
            return customerManagementService.getAssignedCustomerCodesList(user.get().username());
        }
        return Collections.emptyList();
    }

    public List<ContractTypeDTO> getContractTypesList() {
        List<ContractTypeDTO> contractTypes = new ArrayList<>();
        for (ContractType type : contractTypeRepository.contractTypes()) {
            contractTypes.add(type.toDTO());
        }
        return contractTypes;
    }

    public List<WorkModeDTO> getWorkModesList() {
        List<WorkModeDTO> workModes = new ArrayList<>();
        for (WorkMode mode : workModeRepository.workModes()) {
            workModes.add(mode.toDTO());
        }
        return workModes;
    }

    public List<RequirementSpecificationDTO> getRequirementsSpecificationsList(CustomerDTO companyInfo) {
        List<RequirementSpecificationDTO> requirementSpecifications = new ArrayList<>();
        for (RequirementSpecification requirement : requirementSpecificationRepository.getCustomerRequirementsSpecificationsFileList(companyInfo.costumerCode())) {
            requirementSpecifications.add(requirement.toDTO());
        }
        return requirementSpecifications;
    }


    public Optional<JobOpening> registerJobOpening(String function, ContractTypeDTO contractTypeDenomination,
                                                   WorkModeDTO workModeDenomination, String streetName, String city,
                                                   String district, String state, String zipcode, int numVacancies,
                                                   String description, RequirementSpecificationDTO requirementsFileName,
                                                   CustomerDTO companyInfo) {

        return Optional.of(jobOpeningManagementService.registerJobOpening(function, contractTypeDenomination, workModeDenomination,
                streetName, city, district, state, zipcode, numVacancies, description,
                requirementSpecificationRepository.getFileByName(requirementsFileName.filename()), companyInfo));
    }

}
