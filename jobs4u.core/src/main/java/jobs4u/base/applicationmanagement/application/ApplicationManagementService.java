package jobs4u.base.applicationmanagement.application;

import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.domain.ApplicationFile;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.candidatemanagement.domain.Candidate;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ApplicationManagementService {

    private final ApplicationRepository applicationRepository = PersistenceContext.
            repositories().applications();


    public Application registerApplication(Set<ApplicationFile> files,
                                           Date applicationDate, Candidate candidate){
        return new Application(files, applicationDate, candidate);
    }

    public Application registerApplicationWithoutCandidate(Set<ApplicationFile> files,
                                           Date applicationDate){

        return new Application(files, applicationDate);
    }

    public List<ApplicationDTO> getAllApplicationsThatHaveCandidate(Candidate candidate){
        List<ApplicationDTO> list = new ArrayList<>();
        for(Application application : applicationRepository.applications()){
            if(application.candidate().sameAs(candidate)){
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

    public List<ApplicationDTO> getApplicationsListByUsername(Username username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
