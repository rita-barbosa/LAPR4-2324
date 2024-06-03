package jobs4u.base.app.backoffice.console.presentation.applications;

import eapli.framework.presentation.console.AbstractUI;
import jobs4u.base.applicationmanagement.application.AnalyseApplicationFilesController;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;

public class AnalyseApplicationFilesUI extends AbstractUI {

    AnalyseApplicationFilesController controller = new AnalyseApplicationFilesController();

    @Override
    protected boolean doShow() {
        controller.getAllJobOpenings();

        String jobReference = "jobReference";
        controller.getJobOpeningWithJobReference(jobReference);

        ApplicationDTO application = null;

        assert application != null;
        controller.analyzeFilesFromApplication(application);


        return true;
    }

    @Override
    public String headline() {
        return "";
    }


    private JobOpeningDTO showAndSelectJobOpening() {
//        List<CustomerDTO> customerDTOS = this.controller.getCustomersList();
//        if (customerDTOS.isEmpty()){
//            throw new NoSuchElementException("There are no customers to be associated with a job opening.");
//        }
//        SelectWidget<CustomerDTO> costumerSelector = new SelectWidget<>("Select a customers that was assigned to you:",
//                customerDTOS);
//
//        costumerSelector.show();
//        return costumerSelector.selectedElement();
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
