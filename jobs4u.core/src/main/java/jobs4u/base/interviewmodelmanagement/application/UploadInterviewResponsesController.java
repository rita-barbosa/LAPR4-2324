package jobs4u.base.interviewmodelmanagement.application;

import jobs4u.base.applicationmanagement.application.ApplicationManagementService;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.jobopeningmanagement.application.JobOpeningManagementService;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.recruitmentprocessmanagement.application.RecruitmentProcessManagementService;

import java.util.List;

public class UploadInterviewResponsesController {

    JobOpeningManagementService jobOpeningManagementService;
    ApplicationManagementService applicationManagementService;
    RecruitmentProcessManagementService recruitmentProcessManagementService;
    InterviewModelManagementService interviewModelManagementService;


    public UploadInterviewResponsesController() {
        this.jobOpeningManagementService = new JobOpeningManagementService();
        this.applicationManagementService = new ApplicationManagementService();
        this.recruitmentProcessManagementService = new RecruitmentProcessManagementService();
        this.interviewModelManagementService = new InterviewModelManagementService();
    }

    public List<JobOpeningDTO> getJobOpenings() {
        return jobOpeningManagementService.getOnGoingJobOpenings();
    }

    public List<ApplicationDTO> getApplications(String jobReference) {
        if (recruitmentProcessManagementService.checkIfRecruitmentProcessIsInInterviewPhase(jobReference)){
            return applicationManagementService.getApplicationsFromJobReference(jobReference);
        }
        throw new RuntimeException("\n[WARNING] Recruitment process is not in interview phase.");
    }

    public boolean uploadFile(ApplicationDTO applicationDTO, String interviewName, String filepath) {
        if(interviewModelManagementService.checkAnswersFileIsValid(interviewName, filepath)){
            applicationManagementService.uploadInterviewAnswerFile(applicationDTO, filepath);
            return true;
        }
        return false;
    }
}
