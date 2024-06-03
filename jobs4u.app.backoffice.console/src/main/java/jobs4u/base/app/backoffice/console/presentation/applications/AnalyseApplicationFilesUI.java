package jobs4u.base.app.backoffice.console.presentation.applications;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.base.applicationmanagement.application.AnalyseApplicationFilesController;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

public class AnalyseApplicationFilesUI extends AbstractUI {

    AnalyseApplicationFilesController controller = new AnalyseApplicationFilesController();

    @Override
    protected boolean doShow() {
        JobOpeningDTO jobOpeningDTO = showAndSelectJobOpening();
        ApplicationDTO applicationDTO = showAndSelectApplication(jobOpeningDTO.getJobReference());

        Map<String, Pair<Integer, List<String>>> top20words = controller.analyzeFilesFromApplication(applicationDTO);
        displayTop20(top20words);

        return true;
    }

    private void displayTop20(Map<String, Pair<Integer, List<String>>> top20words) {
        int orderNumber = 1;

        System.out.println("\n==============================================\n");
        for (Map.Entry<String, Pair<Integer, List<String>>> entry : top20words.entrySet()) {
            System.out.println("\n----------------------------\n");
            System.out.printf("#%d\n[WORD] %s", orderNumber, entry.getKey());
            System.out.printf(" > [WORD COUNT] %d\n  -> [FILES]\n", entry.getValue().getKey());
            for (String filename : entry.getValue().getValue()) {
                System.out.printf("    * %s\n", filename);
            }
            System.out.println("----------------------------\n");
            ++orderNumber;
        }
        System.out.println("\n==============================================\n");
    }

    private ApplicationDTO showAndSelectApplication(String jobReference) {
        SelectWidget<ApplicationDTO> jobOpeningDTOSelectWidget = new SelectWidget<>("Select an Application",
                controller.getJobOpeningWithJobReference(jobReference));
        jobOpeningDTOSelectWidget.show();
        return jobOpeningDTOSelectWidget.selectedElement();
    }

    private JobOpeningDTO showAndSelectJobOpening() {
        SelectWidget<JobOpeningDTO> applicationDTOSelectWidget = new SelectWidget<>("Select a Job Opening",
                controller.getAllJobOpenings());
        applicationDTOSelectWidget.show();
        return applicationDTOSelectWidget.selectedElement();
    }

    @Override
    public String headline() {
        return "Top 20 Used Words";
    }

}
