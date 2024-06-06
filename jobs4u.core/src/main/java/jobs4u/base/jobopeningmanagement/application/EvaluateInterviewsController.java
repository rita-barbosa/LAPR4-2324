package jobs4u.base.jobopeningmanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import jobs4u.base.applicationmanagement.application.ApplicationManagementService;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.usermanagement.domain.BaseRoles;
import jobs4u.plugin.core.adapter.InterviewPluginAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EvaluateInterviewsController {
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final JobOpeningManagementService jobOpeningManagementService= new JobOpeningManagementService();
    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();

    public Iterable<JobOpeningDTO> getJobOpeningsList() {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

        return jobOpeningManagementService.activeJobOpenings();
    }

    public boolean interviewsEvaluation(JobOpeningDTO applicationDto) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

        Iterable<Application> applications = applicationRepository.applicationsForJobOpeningWithInterviewAnswers(applicationDto.getJobReference());
        if (!applications.iterator().hasNext()) {
            throw new IllegalArgumentException("No applications have associated interview answers.");
        }
        /*for (Application application : applications){

        }*/
        return true;
    }

}
