package jobs4u.base.requirementsmanagement.application;

import jobs4u.base.applicationmanagement.application.ApplicationManagementService;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.jobopeningmanagement.application.JobOpeningManagementService;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.recruitmentprocessmanagement.application.RecruitmentProcessManagementService;

import java.util.List;

public class UploadRequirementAnswersController {

    JobOpeningManagementService jobOpeningManagementService;
    ApplicationManagementService applicationManagementService;
    RequirementSpecificationManagementService requirementSpecificationManagementService;
    RecruitmentProcessManagementService recruitmentProcessManagementService;


    public UploadRequirementAnswersController() {
        this.jobOpeningManagementService = new JobOpeningManagementService();
        this.applicationManagementService = new ApplicationManagementService();
        this.requirementSpecificationManagementService =  new RequirementSpecificationManagementService();
        this.recruitmentProcessManagementService = new RecruitmentProcessManagementService();
    }


    public List<JobOpeningDTO> getJobOpenings() {
        return jobOpeningManagementService.getOnGoingJobOpenings();
    }

    public List<ApplicationDTO> getApplications(String jobReference) {
        if (recruitmentProcessManagementService.checkIfRecruitmentProcessIsInScreeningPhase(jobReference)){
            return applicationManagementService.getApplicationsFromJobReference(jobReference);
        }
        throw new RuntimeException("\n[WARNING] Recruitment process is not in analysis phase.");
    }

    public boolean uploadFile(ApplicationDTO applicationDTO, String requirementName, String filepath) {
        if(requirementSpecificationManagementService.checkAnswersFileIsValid(requirementName, filepath)){
            applicationManagementService.uploadAnswersFile(applicationDTO, filepath);
            return true;
        }
        return false;
    }
}
