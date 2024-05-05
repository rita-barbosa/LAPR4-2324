package jobs4u.base.applicationmanagement.application;

import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.domain.ApplicationFile;
import jobs4u.base.applicationmanagement.domain.RequirementAnswer;
import jobs4u.base.applicationmanagement.domain.RequirementResult;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.candidatemanagement.domain.Candidate;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ApplicationManagementService {

    private final ApplicationRepository applicationRepository = PersistenceContext.
            repositories().applications();


    public Application registerApplication(Set<ApplicationFile> files,
                                           Date applicationDate, Candidate candidate){
        Application application = new Application(files, applicationDate, candidate);

        return application;
    }

    public Application registerApplicationWithoutCandidate(Set<ApplicationFile> files,
                                           Date applicationDate){
        Application application = new Application(files, applicationDate);

        return application;
    }

    public List<ApplicationDTO> getAllApplicationsThatHaveCandidate(Candidate candidate){
        List<ApplicationDTO> list = new ArrayList<>();
        for(Application application : applicationRepository.applications()){
            if(application.getCandidate().sameAs(candidate)){
                list.add(application.toDTO());
            }
        }
        return list;
    }

    public List<ApplicationDTO> getApplicationsList(JobOpening jobOpening){
        List<ApplicationDTO> applicationDTO = new ArrayList<>();

            for (Application application : jobOpening.getApplications()) {
                applicationDTO.add(application.toDTO());
            }

        return applicationDTO;
    }

}
