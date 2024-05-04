package jobs4u.base.app.backoffice.console.presentation.listApplications;

import eapli.framework.actions.Action;

public class ListJobOpeningApplicationsAction implements Action {

    @Override
    public boolean execute(){
        return new ListJobOpeningApplicationsUI().show();
    }
}
