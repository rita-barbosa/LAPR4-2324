package jobs4u.base.app.backoffice.console.presentation.jobopening;

import eapli.framework.actions.Action;

public class RegisterJobOpeningAction implements Action {

    @Override
    public boolean execute() {
        return new RegisterJobOpeningUI().show();
    }
}
