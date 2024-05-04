package jobs4u.base.applicationmanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.application.JobOpeningManagementService;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@UseCaseController
public class ListJobOpeningApplicationsController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();

    public List<JobOpeningDTO> getJobOpeningsList() {
        List<JobOpeningDTO> jobOpenings = new ArrayList<>();
        for (JobOpening jobOpening : jobOpeningRepository.findAll()) {
            jobOpenings.add(jobOpening.toDTO());

        }
        return jobOpenings;
    }

    public List<ApplicationDTO> getApplicationsList(JobOpeningDTO jobOpeningDTO) {
        String jobReference = jobOpeningDTO.getJobReference();
        JobOpening jobOpening = null;

        for (JobOpening job : jobOpeningRepository.findAll()) {
            if (job.getJobReference().toString().equals(jobReference)){
                jobOpening = job;
            }
        }
        List<ApplicationDTO> applicationDTO = new ArrayList<>();
        if (jobOpening != null){
            for (Application application : jobOpening.getApplications()) {
                applicationDTO.add(application.toDTO());
            }
        }


        return applicationDTO;
    }

}
