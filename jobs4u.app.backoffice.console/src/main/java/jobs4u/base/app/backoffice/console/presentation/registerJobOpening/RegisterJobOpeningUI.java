package jobs4u.base.app.backoffice.console.presentation.registerJobOpening;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.base.customermanagement.domain.CompanyName;
import jobs4u.base.customermanagement.domain.CustomerCode;
import jobs4u.base.customermanagement.dto.CustomerDTO;
import jobs4u.base.jobopeningmanagement.application.RegisterJobOpeningController;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;
import jobs4u.base.requirementsmanagement.dto.RequirementSpecificationDTO;

import java.util.NoSuchElementException;
import java.util.Optional;

public class RegisterJobOpeningUI extends AbstractUI {

    private final RegisterJobOpeningController controller = new RegisterJobOpeningController();

    @Override
    protected boolean doShow() {

        //Selectable attributes
        CustomerDTO companyInfo;
        try {
          //  companyInfo = showAndSelectCustomer();
        }catch (NoSuchElementException e){
            return false;
        }
        ContractTypeDTO contractTypeDenomination = showAndSelectContractType();
        WorkModeDTO workModeDenomination = showAndSelectWorkMode();
        RequirementSpecificationDTO requirementsFileName = showAndSelectRequirementSpecification();

        //Writable Attributes
        String function = Console.readNonEmptyLine("What's the job's title?", "Providing a job's title is obligatory.");

        String description = Console.readNonEmptyLine("Provide a brief description for the job opening.",
                "A brief description is obligatory.");

        int numVacancies = WriteNumberVacancies();

        System.out.println("\nRegarding the job's opening address:");
        String streetName = Console.readNonEmptyLine("What's the street name?",
                "Providing a job opening address's street name is obligatory.");
        String city = Console.readNonEmptyLine("What's the city?",
                "Providing a job opening address's city is obligatory.");
        String district = Console.readNonEmptyLine("What's the district?",
                "Providing a job opening address's district is obligatory.");
        String streetNumber = Console.readNonEmptyLine("What's the street number?",
                "Providing a job opening address's state is obligatory.");

        String zipcode = WriteZipcode();

        try {
            Optional< JobOpening> jobOpening = this.controller.registerJobOpening(function, contractTypeDenomination,
                    workModeDenomination, streetName, city, district, streetNumber, zipcode, numVacancies, description,
                    requirementsFileName, companyInfo = new CustomerDTO(new CompanyName("Luau"), new CustomerCode("LUAU")));
            if (jobOpening.isEmpty()){
                throw new IntegrityViolationException();
            }else{
                System.out.println("The job opening was successfully registered!\n");
            }
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("An error occurred while registering the job opening.");
        }

        return false;
    }

    private String WriteZipcode() {
        String zipcode = "";
        while (String.valueOf(zipcode).length() != 5) {
            zipcode = Console.readLine("Provide the zipcode");
            if (String.valueOf(zipcode).length() != 5){
                System.out.println("The zipcode must have 5 characters.");
            }
        }
        return zipcode;
    }

    private int WriteNumberVacancies() {
        int numVacancies = 0;
        while (numVacancies < 1) {
            numVacancies = Console.readInteger("Provide the number of vacancies");
            if (numVacancies < 1){
                System.out.println("The number of vacancies must be above zero.");
            }
        }
        return numVacancies;
    }

    private RequirementSpecificationDTO showAndSelectRequirementSpecification() {
        SelectWidget<RequirementSpecificationDTO> RequirementSpecificationSelector = new SelectWidget<>("Select the requirement specifications:",
                this.controller.getRequirementsSpecificationsList());
        RequirementSpecificationSelector.show();
        return RequirementSpecificationSelector.selectedElement();
    }

    private WorkModeDTO showAndSelectWorkMode() {
        SelectWidget<WorkModeDTO> workModeSelector = new SelectWidget<>("Select a work mode:",
                this.controller.getWorkModesList());
        workModeSelector.show();
        return workModeSelector.selectedElement();
    }

    private ContractTypeDTO showAndSelectContractType() {
        SelectWidget<ContractTypeDTO> contractTypeSelector = new SelectWidget<>("Select a contract type:",
                this.controller.getContractTypesList());
        contractTypeSelector.show();
        return contractTypeSelector.selectedElement();
    }

    private CustomerDTO showAndSelectCustomer() {
        SelectWidget<CustomerDTO> costumerSelector = new SelectWidget<>("Select a customers that was assigned to you:",
                this.controller.getCustomersList());
        costumerSelector.show();
        return costumerSelector.selectedElement();
    }

    @Override
    public String headline() {
        return "Register New Job Openings";
    }
}
