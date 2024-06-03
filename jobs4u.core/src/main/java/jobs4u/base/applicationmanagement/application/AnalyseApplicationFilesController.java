package jobs4u.base.applicationmanagement.application;

import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.domain.ApplicationFile;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.jobopeningmanagement.application.JobOpeningManagementService;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnalyseApplicationFilesController {

    JobOpeningManagementService jobOpeningManagementService;
    ApplicationFilesThreadService applicationFilesThreadService;

    public AnalyseApplicationFilesController(){
        jobOpeningManagementService = new JobOpeningManagementService();
        applicationFilesThreadService = new ApplicationFilesThreadService();
    }

    public void getAllJobOpenings() {
        //follow shared diagram
    }

    public List<ApplicationDTO> getJobOpeningWithJobReference(String jobReference) {
        JobOpening jobOpening = jobOpeningManagementService.getJobOpeningByJobRef(jobReference).get();

        List<ApplicationDTO> applicationDTOList = List.of();

        for (Application application : jobOpening.getApplications()){
            //do add dto
        }

        return applicationDTOList;
    }

    public Map<String, Map<String, Integer>> analyzeFilesFromApplication(ApplicationDTO application) {
        //change to string
        Set<ApplicationFile> applicationFiles = application.getApplicationFiles();
        return applicationFilesThreadService.getTop20Words(applicationFiles);
    }
}
