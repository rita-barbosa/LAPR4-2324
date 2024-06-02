package jobs4u.base.app.backoffice.console.presentation.jobopening.editing;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.visitor.Visitor;
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
import java.util.Map;
import java.util.function.Function;

import static jobs4u.base.jobopeningmanagement.domain.EditableInformation.*;

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

            Iterable<?> info = controller.necessaryInformation(editableInformation);

            if (info == null) {
                newInfo = Console.readLine("New " + editableInformation.toString());
            } else {
                if (editableInformation.equals(REQ_SPECI)) {
                    newInfo = handleSelection((Iterable<RequirementSpecificationDTO>) info, "Select the new requirement specification", RequirementSpecificationDTO::getName, new RequirementSpecificationDTOPrinter());
                } else if (editableInformation.equals(INT_MODEL)) {
                    newInfo = handleSelection((Iterable<InterviewModelDTO>) info, "Select the new interview model", InterviewModelDTO::name, new InterviewModelDtoPrinter());
                } else if (editableInformation.equals(WORK_MODE)) {
                    newInfo = handleSelection((Iterable<WorkModeDTO>) info, "Select the new work mode", WorkModeDTO::workModeName, new WorkModeDtoPrinter());
                } else if (editableInformation.equals(CONTRACT_TYPE)) {
                    newInfo = handleSelection((Iterable<ContractTypeDTO>) info, "Select the new contract type", ContractTypeDTO::contractTypeName, new ContractTypeDtoPrinter());
                } else {
                    throw new IllegalArgumentException("Unsupported EditableInformation type");
                }
            }
            newInformation.add(newInfo);
            wantsToExit = Console.readInteger("Do you want to continue editing?\n0 - Yes\n1 - No");
        }
        controller.changeJobOpeningInformation(jobOpeningDTO, selectedInformation, newInformation);


        return false;
    }

    private <T> String handleSelection(Iterable<T> info, String prompt, Function<T, String> getNameFunction, Visitor<T> printer) {
        SelectWidget<T> widget = new SelectWidget<>(prompt, info, printer);
        widget.show();
        return getNameFunction.apply(widget.selectedElement());
    }

    @Override
    public String headline() {
        return "Edit Job Opening";
    }
}

