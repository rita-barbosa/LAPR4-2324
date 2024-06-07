package jobs4u.base.app.backoffice.console.presentation.applications;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.base.applicationmanagement.application.DisplayAllApplicationDataController;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;

import java.util.List;
import java.util.NoSuchElementException;

public class DisplayAllApplicationDataUI extends AbstractUI {

    private static final DisplayAllApplicationDataController controller = new DisplayAllApplicationDataController();

    @Override
    protected boolean doShow() {

        try{
            int index = 0;

            List<ApplicationDTO> applicationDTOList = controller.getApplicationsList();

            for (ApplicationDTO dto : applicationDTOList) {
                System.out.printf("INDEX: %d", index);
                System.out.println(dto.toString());
                index++;
            }

            int option = selectsIndexNoCancel(applicationDTOList);

            ApplicationDTO applicationDTO = applicationDTOList.get(option);
            String sb;
            
            if (applicationDTO.getInterview() != null && applicationDTO.getRequirementAnswer() != null && applicationDTO.getRequirementResult() != null){
                sb = "==================================================================\n" +
                        "[Application]" + applicationDTO.getId() + "\n" +
                        "[Files]" + applicationDTO.getApplicationFiles() + "\n" +
                        "[Application Date]" + applicationDTO.getApplicationDate() + "\n" +
                        "[Application Status]" + applicationDTO.getApplicationStatus() + "\n" +
                        "[Candidate Name]" + applicationDTO.getCandidateName() + "\n" +
                        "[Candidate Username]" + applicationDTO.getCandidate() + "\n" +
                        "[Interview Result]" + applicationDTO.getInterview().interviewResult() + "\n" +
                        "[Interview Answer]" + applicationDTO.getInterview().interviewAnswer().name() + "\n" +
                        "[Requirement Result]" + applicationDTO.getRequirementResult() + "\n" +
                        "[Requirement Answer]" + applicationDTO.getRequirementAnswer().name() + "\n" +
                        "=====================================================================";
            } else if (applicationDTO.getInterview() != null) {
                sb = "==================================================================\n" +
                        "[Application]" + applicationDTO.getId() + "\n" +
                        "[Files]" + applicationDTO.getApplicationFiles() + "\n" +
                        "[Application Date]" + applicationDTO.getApplicationDate() + "\n" +
                        "[Application Status]" + applicationDTO.getApplicationStatus() + "\n" +
                        "[Candidate Name]" + applicationDTO.getCandidateName() + "\n" +
                        "[Candidate Username]" + applicationDTO.getCandidate() + "\n" +
                        "[Interview Result]" + applicationDTO.getInterview().interviewResult() + "\n" +
                        "[Interview Answer]" + applicationDTO.getInterview().interviewAnswer().name() + "\n" +
                        "=====================================================================";
            } else if (applicationDTO.getRequirementResult() != null && applicationDTO.getRequirementAnswer() != null) {
                sb = "==================================================================\n" +
                        "[Application]" + applicationDTO.getId() + "\n" +
                        "[Files]" + applicationDTO.getApplicationFiles() + "\n" +
                        "[Application Date]" + applicationDTO.getApplicationDate() + "\n" +
                        "[Application Status]" + applicationDTO.getApplicationStatus() + "\n" +
                        "[Candidate Name]" + applicationDTO.getCandidateName() + "\n" +
                        "[Candidate Username]" + applicationDTO.getCandidate() + "\n" +
                        "[Requirement Result]" + applicationDTO.getRequirementResult() + "\n" +
                        "[Requirement Answer]" + applicationDTO.getRequirementAnswer().name() + "\n" +
                        "=====================================================================";
            } else {
                sb = "==================================================================\n" +
                        "[Application]" + applicationDTO.getId() + "\n" +
                        "[Files]" + applicationDTO.getApplicationFiles() + "\n" +
                        "[Application Date]" + applicationDTO.getApplicationDate() + "\n" +
                        "[Application Status]" + applicationDTO.getApplicationStatus() + "\n" +
                        "[Candidate Name]" + applicationDTO.getCandidateName() + "\n" +
                        "[Candidate Username]" + applicationDTO.getCandidate() + "\n" +
                        "=====================================================================";
            }

            System.out.println(sb);

        } catch (NoSuchElementException | IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private static int selectsIndexNoCancel(List<?> list) {
        String input;
        int value;
        do {
            input = Console.readNonEmptyLine("Select the application index:", "Providing a application option is obligatory.");

            try {
                value = Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > list.size() - 1);
        return value;
    }

    @Override
    public String headline() {
        return "Display All Data of Application";
    }
}
