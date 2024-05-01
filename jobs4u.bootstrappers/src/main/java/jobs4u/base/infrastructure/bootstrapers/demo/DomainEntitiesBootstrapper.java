package jobs4u.base.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jobs4u.base.customermanagement.application.CustomerManagementService;
import jobs4u.base.infrastructure.bootstrapers.UsersBootstrapperBase;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.*;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;
import jobs4u.base.jobopeningmanagement.repositories.ContractTypeRepository;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;
import jobs4u.base.jobopeningmanagement.repositories.WorkModeRepository;
import jobs4u.base.requirementsmanagement.domain.PluginJarFile;
import jobs4u.base.requirementsmanagement.domain.RequirementDescription;
import jobs4u.base.requirementsmanagement.domain.RequirementName;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.requirementsmanagement.repositories.RequirementSpecificationRepository;
import jobs4u.base.usermanagement.domain.BaseRoles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DomainEntitiesBootstrapper  extends UsersBootstrapperBase implements Action {

    CustomerManagementService customerManagementService = new CustomerManagementService();

    private ContractTypeRepository contractTypeRepository;
    private WorkModeRepository workModeRepository;
    private RequirementSpecificationRepository requirementSpecificationRepository;

    private JobOpeningRepository jobOpeningRepository;

    List<RequirementSpecification> requirementSpecificationsList = new ArrayList<>();

    @Override
    public boolean execute() {
        instantiateRepositories();
        persistContractTypes();
        persistWorkModes();
        persistRequirementSpecifications();
        persistCustomers();
        persistJobOpenings();
        return true;
    }

    public void instantiateRepositories() {
        this.contractTypeRepository = PersistenceContext.repositories().contractTypes();
        this.workModeRepository = PersistenceContext.repositories().workModes();
        this.requirementSpecificationRepository = PersistenceContext.repositories().requirementSpecifications();
        this.jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
    }

    private void persistJobOpenings() {
        ContractTypeDTO contract = new ContractTypeDTO("full-time");
        WorkModeDTO mode = new WorkModeDTO("remote");
        String description = "This work is very well payed.";

        JobReference jobReference = new JobReference("ISEP", 1);

        JobOpening jobOpening = new JobOpening("Front End Junior Developer", contract, mode, "123 Main Street",
                "Flagtown", "Star District", "USA", "12345", 10, description,
                requirementSpecificationsList.get(1), jobReference);

        jobOpeningRepository.save(jobOpening);
    }

    private void persistRequirementSpecifications() {
        RequirementName requirementName = new RequirementName("[Back End] Senior Developer");
        RequirementDescription requirementDescription = new RequirementDescription("Requirements for back end senior developers");
        PluginJarFile pluginJarFile = new PluginJarFile("back-end-senior-developer.jar");
        RequirementSpecification requirementSpecification = new RequirementSpecification(requirementName, requirementDescription,
                pluginJarFile);

        RequirementName requirementName1 = new RequirementName("[Front End] Junior Developer");
        RequirementDescription requirementDescription1 = new RequirementDescription("Requirements for front end junior developers");
        PluginJarFile pluginJarFile1 = new PluginJarFile("front-end-junior-developer.jar");
        RequirementSpecification requirementSpecification1 = new RequirementSpecification(requirementName1, requirementDescription1,
                pluginJarFile1);

        requirementSpecificationsList.add(requirementSpecification);
        requirementSpecificationsList.add(requirementSpecification1);

        requirementSpecificationRepository.save(requirementSpecification);
        requirementSpecificationRepository.save(requirementSpecification1);
    }

    private void persistCustomers() {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CUSTOMER_MANAGER);
        SystemUser customerManager = registerUser("customerM2@email.com", "Password-1", "Joana", "Cash", roles);
        Address address = new Address("StreetLane", "City Garden", "District 9", "14th", "15632");

        customerManagementService.registerNewCustomer("Instituto Superior de Engenharia do Porto", address.toString(),
                "ISEP", customerManager, "isep@email.com");
    }

    private void persistWorkModes() {
        WorkMode workMode = WorkMode.valueOf("remote");
        WorkMode workMode1 = WorkMode.valueOf("hybrid");
        WorkMode workMode2 = WorkMode.valueOf("onsite");

        workModeRepository.save(workMode);
        workModeRepository.save(workMode1);
        workModeRepository.save(workMode2);
    }

    private void persistContractTypes() {
        ContractType contractType = ContractType.valueOf("full-time");
        ContractType contractType1 = ContractType.valueOf("part-time");

        contractTypeRepository.save(contractType);
        contractTypeRepository.save(contractType1);
    }
}
