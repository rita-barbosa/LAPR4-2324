package jobs4u.base.applicationmanagement.application;

import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.domain.ApplicationFile;
import jobs4u.base.applicationmanagement.domain.RequirementAnswer;
import jobs4u.base.applicationmanagement.domain.RequirementResult;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.candidatemanagement.domain.Candidate;
import jobs4u.base.infrastructure.persistence.PersistenceContext;

import java.io.File;
import java.util.Date;
import java.util.Set;

public class ApplicationManagementService {

    private final ApplicationRepository applicationRepository = PersistenceContext.
            repositories().applications();


    public Application registerApplication(Set<ApplicationFile> files,
                                           Date applicationDate, Candidate candidate){
        Application application = new Application(files, applicationDate, candidate);

        applicationRepository.save(application);
        return application;
    }

    public Application registerApplicationWithoutCandidate(Set<ApplicationFile> files,
                                           Date applicationDate){
        Application application = new Application(files, applicationDate);

        applicationRepository.save(application);
        return application;
    }

}
