package jobs4u.base.app.backoffice.console.presentation.candidate;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import jobs4u.base.app.backoffice.console.presentation.requirementspecification.JobOpeningDTOPrinter;
import jobs4u.base.candidatemanagement.application.EnableDisableCandidateController;
import jobs4u.base.candidatemanagement.application.ListCandidatesController;
import jobs4u.base.candidatemanagement.domain.Candidate;
import jobs4u.base.candidatemanagement.dto.CandidateDTO;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;

import java.util.List;
import java.util.Scanner;

public class EnableDisableCandidateUI extends AbstractUI {
    private final EnableDisableCandidateController controller = new EnableDisableCandidateController();

    @Override
    protected boolean doShow() {

        try {
            SelectWidget<CandidateDTO> selectorCandidate;
            final CandidateDTO candidateDTO;

            Scanner sc = new Scanner(System.in);
            int option;
            System.out.println("1 - Enable Candidate\n2 - Disable Candidate\n");
            option=sc.nextInt();
            switch (option){
                case (1):{

                    selectorCandidate = new SelectWidget<>("Please select a Candidate", controller.deactivatedCandidates(), new CandidateDTOPrinter());
                    selectorCandidate.show();
                    candidateDTO = selectorCandidate.selectedElement();

                    controller.activateCandidate(candidateDTO);
                    break;
                }
                case (2):{
                    selectorCandidate = new SelectWidget<>("Please select a Candidate", controller.activeCandidates(), new CandidateDTOPrinter());
                    selectorCandidate.show();
                    candidateDTO = selectorCandidate.selectedElement();

                    controller.deactivateCandidate(candidateDTO);
                    break;
                }
            }
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println(
                    "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
        }


        return true;
    }

    @Override
    public String headline() {
        return "Enable or Disable Candidate";
    }
}
