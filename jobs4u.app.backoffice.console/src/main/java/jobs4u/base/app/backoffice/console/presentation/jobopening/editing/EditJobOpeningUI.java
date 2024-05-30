package jobs4u.base.app.backoffice.console.presentation.jobopening.editing;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.base.app.backoffice.console.presentation.interviewmodel.InterviewModelDtoPrinter;
import jobs4u.base.app.backoffice.console.presentation.requirementspecification.JobOpeningDTOPrinter;
import jobs4u.base.app.backoffice.console.presentation.requirementspecification.RequirementSpecificationDTOPrinter;
import jobs4u.base.contracttypemanagement.dto.ContractTypeDTO;
import jobs4u.base.interviewmodelmanagement.dto.InterviewModelDTO;
import jobs4u.base.jobopeningmanagement.application.EditJobOpeningController;
import jobs4u.base.jobopeningmanagement.domain.EditableInformation;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import eapli.framework.io.util.Console;
import jobs4u.base.requirementsmanagement.dto.RequirementSpecificationDTO;
import jobs4u.base.workmodemanagement.dto.WorkModeDTO;

import java.util.ArrayList;
import java.util.List;

public class EditJobOpeningUI extends AbstractUI {

    private final EditJobOpeningController controller = new EditJobOpeningController();


    @Override
    protected boolean doShow() {
        List<EditableInformation> selectedInformation = new ArrayList<>();
        List<String> newInformation = new ArrayList<>();
        //------
        SelectWidget<JobOpeningDTO> joDtos;
        SelectWidget<EditableInformation> ei;
        EditableInformation editableInformation;
        String newInfo = "";
        //------
        final JobOpeningDTO jobOpeningDTO;
        int wantsToExit = 0;
//        try {
            joDtos = new SelectWidget<>("Select a job opening", controller.getJobOpeningList(), new JobOpeningDTOPrinter());
            joDtos.show();
            jobOpeningDTO = joDtos.selectedElement();

            while (wantsToExit == 0) {
                ei = new SelectWidget<>("Select the information to be edited", controller.editableJobOpeningInformation(jobOpeningDTO), new EditableInformationPrinter());
                ei.show();
                editableInformation = ei.selectedElement();
                selectedInformation.add(editableInformation);

                if (editableInformation.equals(EditableInformation.REQ_SPECI)) {
                    SelectWidget<RequirementSpecificationDTO> rs = new SelectWidget<>("Select the new requirement specification", controller.requirementSpecifications(), new RequirementSpecificationDTOPrinter());
                    newInfo = rs.selectedElement().getName();
                } else if (editableInformation.equals(EditableInformation.INT_MODEL)) {
                    SelectWidget<InterviewModelDTO> im = new SelectWidget<>("Select the new interview model", controller.interviewModels(), new InterviewModelDtoPrinter());
                    newInfo = im.selectedElement().name();
                } else if (editableInformation.equals(EditableInformation.WORK_MODE)) {
                    SelectWidget<WorkModeDTO> wm = new SelectWidget<>("Select the new work mode", controller.workModes(), new WorkModeDtoPrinter());
                    newInfo = wm.selectedElement().workModeName();
                } else if (editableInformation.equals(EditableInformation.CONTRACT_TYPE)) {
                    SelectWidget<ContractTypeDTO> ct = new SelectWidget<>("Select the new contract type", controller.contractTypes(), new ContractTypeDtoPrinter());
                    newInfo = ct.selectedElement().contractTypeName();
                } else {
                    newInfo = Console.readLine("New " + editableInformation.toString());
                }
                newInformation.add(newInfo);
                wantsToExit = Console.readInteger("Do you want to continue editing?\n0 - Yes\n1 - No");
            }
            controller.changeJobOpeningInformation(jobOpeningDTO, selectedInformation, newInformation);

//        } catch (IllegalStateException | IllegalArgumentException e) {
//            System.out.println("Erro:" + e.getMessage());
//        }


        return false;
    }

    @Override
    public String headline() {
        return "Edit Job Opening";
    }
}

