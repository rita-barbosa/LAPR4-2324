package jobs4u.base.app.backoffice.console.presentation.registerJobOpening;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.base.entitymanagement.dto.CustomerDTO;
import jobs4u.base.jobopeningmanagement.application.RegisterJobOpeningController;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;
import jobs4u.base.requirementsmanagement.dto.RequirementSpecificationDTO;

import java.util.Optional;

public class RegisterJobOpeningUI extends AbstractUI {


    private static final RegisterJobOpeningController controller = new RegisterJobOpeningController();

    private static ContractTypeDTO contractTypeDenomination;
    private static WorkModeDTO workModeDenomination;
    private static RequirementSpecificationDTO requirementsFileName;
    private static CustomerDTO companyInfo;

    public void run(String[] args) {

        this.doShow();

        String function = Console.readNonEmptyLine("What's the job's title?", "Providing a job's title is obligatory.");
        int numVacancies = 0;
        while (numVacancies < 1) {
            numVacancies = Console.readInteger("Provide the number of vacancies");
            if (numVacancies < 1){
                System.out.println("The number of vacancies must be above zero.");
            }
        }
        String description = Console.readNonEmptyLine("Provide a brief description for the job opening.",
                "A brief description is obligatory.");

        System.out.println("Regarding the job's opening address:");
        String streetName = Console.readNonEmptyLine("What's the street name?",
                "Providing a job opening address's street name is obligatory.");
        String city = Console.readNonEmptyLine("What's the city?",
                "Providing a job opening address's city is obligatory.");
        String district = Console.readNonEmptyLine("What's the district?",
                "Providing a job opening address's district is obligatory.");
        String state = Console.readNonEmptyLine("What's the state?",
                "Providing a job opening address's state is obligatory.");
        String zipcode = "";
        while (String.valueOf(zipcode).length() != 5) {
            zipcode = Console.readLine("Provide the zipcode");
            if (String.valueOf(zipcode).length() != 5){
                System.out.println("The zipcode must have 5 characters.");
            }
        }


        Optional<JobOpening> jobOpening = controller.registerJobOpening(function, contractTypeDenomination, workModeDenomination,
                streetName, city, district, state, zipcode, numVacancies, description, requirementsFileName, companyInfo);


    }

    @Override
    protected boolean doShow() {
        SelectWidget<CustomerDTO> costumerSelector = new SelectWidget<>("Customers assigned to you:",
                controller.getCustomersList());
        costumerSelector.show();
        companyInfo = costumerSelector.selectedElement();

        SelectWidget<ContractTypeDTO> contractTypeSelector = new SelectWidget<>("Customers assigned to you:",
                controller.getContractTypesList());
        contractTypeSelector.show();
        contractTypeDenomination = contractTypeSelector.selectedElement();

        SelectWidget<WorkModeDTO> workModeSelector = new SelectWidget<>("Customers assigned to you:",
                controller.getWorkModesList());
        workModeSelector.show();
        workModeDenomination = workModeSelector.selectedElement();

        SelectWidget<RequirementSpecificationDTO> RequirementSpecificationSelector = new SelectWidget<>("Customers assigned to you:",
                controller.getRequirementsSpecificationsList(companyInfo));
        RequirementSpecificationSelector.show();
        requirementsFileName = RequirementSpecificationSelector.selectedElement();

        return true;
    }

    @Override
    public String headline() {
        return "Register New Job Openings";
    }
}
