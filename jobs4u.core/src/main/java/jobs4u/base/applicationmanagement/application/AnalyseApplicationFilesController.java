package jobs4u.base.applicationmanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.jobopeningmanagement.application.JobOpeningManagementService;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.usermanagement.domain.BaseRoles;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class AnalyseApplicationFilesController {

    AuthorizationService authorizationService;
    JobOpeningManagementService jobOpeningManagementService;
    ApplicationFilesThreadService applicationFilesThreadService;

    public AnalyseApplicationFilesController() {
        jobOpeningManagementService = new JobOpeningManagementService();
        applicationFilesThreadService = new ApplicationFilesThreadService();
        authorizationService = AuthzRegistry.authorizationService();
    }

    public Iterable<JobOpeningDTO> getAllJobOpenings() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER);
        Optional<UserSession> session = authorizationService.session();
        if (session.isPresent()) {
            Username username = session.get().authenticatedUser().username();
            return jobOpeningManagementService.jobOpeningsOfCustomerManager(username);
        }
        throw new NoSuchElementException("Could not find user in session");
    }

    public List<ApplicationDTO> getJobOpeningWithJobReference(String jobReference) {
        Optional<JobOpening> jobOpening = jobOpeningManagementService.getJobOpeningByJobRef(jobReference);
        List<ApplicationDTO> applicationDTOList = new ArrayList<>();
        assert jobOpening.isPresent();
        for (Application application : jobOpening.get().getApplications()) {
            applicationDTOList.add(application.toDTO());
        }
        return applicationDTOList;
    }

    public Map<String, Pair<Integer, List<String>>> analyzeFilesFromApplication(ApplicationDTO application) {
        return applicationFilesThreadService.getTop20Words(application.getApplicationFiles());
    }
}
