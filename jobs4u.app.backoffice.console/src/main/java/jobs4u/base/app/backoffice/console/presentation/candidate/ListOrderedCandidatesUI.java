package jobs4u.base.app.backoffice.console.presentation.candidate;


import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.base.app.backoffice.console.presentation.requirementspecification.JobOpeningDTOPrinter;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.candidatemanagement.application.ListOrderedCandidatesController;
import jobs4u.base.candidatemanagement.dto.CandidateDTO;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;

import java.util.List;


public class ListOrderedCandidatesUI extends AbstractUI {

    private static final ListOrderedCandidatesController controller = new ListOrderedCandidatesController();

    @Override
    protected boolean doShow() {

        try {
            int index = 0;
            Iterable<JobOpeningDTO> jobOpeningListDTO = controller.getJobOpeningList();

            for (JobOpeningDTO jobOpening: jobOpeningListDTO) {
                System.out.printf("INDEX: %d", index);
                String sb = "==================================================================\n" +
                        "[Job Reference] " + jobOpening.getJobReference() + "\n" +
                        "[Status] " + jobOpening.getStatus() + "\n" +
                        "[Function] " + jobOpening.getFunction() + "\n" +
                        "[Description] " + jobOpening.getDescription() + "\n" +
                        "[Contract Type] " + jobOpening.getContractType() + "\n" +
                        "[Work Mode] " + jobOpening.getWorkMode() + "\n" +
                        "[Address] " + jobOpening.getAddress() + "\n" +
                        "[Customer Code] " + jobOpening.getCustomerCode() + "\n" +
                        "[Number of Vacancies] " + jobOpening.getNumVacancies() + "\n" +
                        "[Requirement Specification] " + jobOpening.getRequirementName() + "\n" +
                        "=====================================================================";
                System.out.println(sb);
                index++;
            }

            int option = selectsIndexNoCancel(jobOpeningListDTO);
            int count = 0;
            JobOpeningDTO jobOpeningDTO = null;

            for (JobOpeningDTO dto : jobOpeningListDTO) {
                if (option == count){
                    jobOpeningDTO = dto;
                }
                count++;
            }

            List<ApplicationDTO> applicationListDTO = controller.getApplicationsOrderedByInterviewResult(jobOpeningDTO);

            if (applicationListDTO.isEmpty()){
                System.out.println("Don't exist applications for the job opening chosen!");
            }

            for (ApplicationDTO app : applicationListDTO) {
                System.out.println(app.toString());
            }

//            List<CandidateDTO> candidateDTOList = controller.getCandidateList()
//
//
//
//
//            List<CandidateDTO> candidatesDTOList = this.controller.getCandidatesList();
//            for (CandidateDTO candidate: candidatesDTOList){
//                System.out.printf("%d. %s | %s | %s%n", index++, candidate.getCandidateName(), candidate.getCandidateEmail(), candidate.getCandidatePhoneNumber());
//            }
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println(
                    "Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
        }


        return true;
    }

    private static int selectsIndexNoCancel(Iterable<?> iterable) {
        String input;
        int value;
        do {
            input = Console.readNonEmptyLine("Select the job opening index:", "Providing a job opening option is obligatory.");

            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > iterable.spliterator().estimateSize() - 1);
        return value;
    }

    @Override
    public String headline() {
        return "List Ordered Candidates";
    }


}
