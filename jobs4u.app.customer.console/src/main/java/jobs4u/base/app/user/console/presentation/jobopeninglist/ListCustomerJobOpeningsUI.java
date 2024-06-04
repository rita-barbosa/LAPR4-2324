package jobs4u.base.app.user.console.presentation.jobopeninglist;

import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
import jobs4u.base.jobopeningmanagement.application.ListCustomerJobOpeningsController;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import org.apache.commons.lang3.tuple.Pair;

//TODO CHECK PORT NUMBER
public class ListCustomerJobOpeningsUI extends AbstractListUI<JobOpeningDTO> {

    private final ListCustomerJobOpeningsController controller = new ListCustomerJobOpeningsController();

    private Username username;

    @Override
    public boolean show() {
        int PORT_NUMBER = 6666;

        username = controller.getSessionCredentials();
        String password = Console.readLine("Please provide your password again:");

        //establish connection
        Pair<Boolean, String> pair = controller.establishConnection(username, password, PORT_NUMBER);
        boolean connectionEstablished = pair.getKey();

        System.out.println(pair.getValue());
        if (connectionEstablished) {
            super.show();
            Pair<Boolean, String> response = controller.closeConnection();
            System.out.println(response.getValue());
        }

        return false;
    }


    @Override
    protected Iterable<JobOpeningDTO> elements() {
        return controller.jobOpeningsDataList(username);
    }

    @Override
    protected Visitor<JobOpeningDTO> elementPrinter() {
        return new JobOpeningCustomerPrinter();
    }

    @Override
    protected String elementName() {
        return "JobOpening";
    }

    @Override
    protected String listHeader() {
        return "The following job openings are associated with the customer:";
    }

    @Override
    protected String emptyMessage() {
        return "No data found.\n";
    }

    @Override
    public String headline() {
        return "Your Job Openings List";
    }
}
