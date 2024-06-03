package jobs4u.base.app.backoffice.console.presentation.candidate;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.base.candidatemanagement.application.EnableDisableCandidateController;
import jobs4u.base.candidatemanagement.application.ListCandidatesController;
import jobs4u.base.candidatemanagement.domain.Candidate;
import jobs4u.base.candidatemanagement.dto.CandidateDTO;

import java.util.List;
import java.util.Scanner;

public class EnableDisableCandidateUI extends AbstractUI {
    private final EnableDisableCandidateController controller = new EnableDisableCandidateController();

    @Override
    protected boolean doShow() {

        try {
            Scanner sc = new Scanner(System.in);
            int option;
            System.out.println("1 - Enable Candidate\n2 - Disable Candidate\n");
            option=sc.nextInt();
            switch (option){
                case (1):{
                    Iterable<Candidate> candidates= controller.deactivatedCandidates();
                    for (Candidate candidate: candidates){
                        System.out.println(candidate.name() +" - "+candidate.phoneNumber());
                    }
                    final String phoneNumber = Console.readLine("Insert the phone number of the candidate to enable: ");
                    controller.activateCandidate(phoneNumber);
                    break;
                }
                case (2):{
                    Iterable<Candidate> candidates= controller.activeCandidates();
                    for (Candidate candidate: candidates){
                        System.out.println(candidate.name() +" - "+candidate.phoneNumber());
                    }
                    final String phoneNumber = Console.readLine("Insert the phone number of the candidate to enable: ");
                    controller.deactivateCandidate(phoneNumber);
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
