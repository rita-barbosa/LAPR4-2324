package jobs4u.base.app.backoffice.console.presentation.interviewmodel;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.interviewmodelmanagement.application.UploadInterviewResponsesController;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;

public class UploadInterviewResponsesUI extends AbstractUI {

    UploadInterviewResponsesController controller = new UploadInterviewResponsesController();

    @Override
    protected boolean doShow() {

        SelectWidget<JobOpeningDTO> jobOpeningDTOSelectWidget = new SelectWidget<>("Select Job Opening",
                controller.getJobOpenings());
        jobOpeningDTOSelectWidget.show();
        JobOpeningDTO jobOpeningDTO = jobOpeningDTOSelectWidget.selectedElement();

        ApplicationDTO applicationDTO;
        try {
            SelectWidget<ApplicationDTO> applicationDTOSelectWidget = new SelectWidget<>("Select Application",
                    controller.getApplications(jobOpeningDTO.getJobReference()));
            applicationDTOSelectWidget.show();
            applicationDTO = applicationDTOSelectWidget.selectedElement();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }

        assert applicationDTO != null;
        String filepath = Console.readLine("Please enter the filepath of the interview answers file: ");

        assert false;
        if (controller.uploadFile(applicationDTO, jobOpeningDTO.getInterviewModelName(), filepath)) {
            System.out.println("File uploaded successfully.");
        } else {
            System.out.println("File not uploaded.\nPlease check the syntax or format of the file.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Import interview answers file";
    }
}