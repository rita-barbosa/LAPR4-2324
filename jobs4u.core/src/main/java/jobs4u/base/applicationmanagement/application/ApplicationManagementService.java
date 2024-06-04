package jobs4u.base.applicationmanagement.application;

import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.domain.ApplicationFile;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.candidatemanagement.domain.Candidate;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.requirementsmanagement.application.ApplicationListDTOService;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.util.*;

public class ApplicationManagementService {

    private final ApplicationRepository applicationRepository = PersistenceContext.
            repositories().applications();

    private final ApplicationDTOService applicationDTOService = new ApplicationDTOService();

    private final ApplicationListDTOService applicationListDTOService = new ApplicationListDTOService();


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

    //TODO ANA GUTERRES - LIST APPLICATIONS METHOD - CLASS LISTINGREQUESTTHREAD
    public List<ApplicationDTO> getApplicationsListByUsername(Username username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<ApplicationDTO> getApplicationsFromJobReference(String jobReference) {
        Iterable<Application> applications = applicationRepository.applicationsForJobOpeningWithRequirements(jobReference);
        return applicationListDTOService.convertApplicationsToDTO(applications);
    }

    public void uploadAnswersFile(ApplicationDTO applicationDTO, String filepath) {
        Application application = applicationRepository.getApplicationFromDTO(applicationDTO);
        application.updateRequirementAnswer(filepath);
        applicationRepository.save(application);
    }

    public Optional<Application> getApplicationWithId(Long id){
        return applicationRepository.ofIdentity(id);
    }

    public Iterable<ApplicationDTO> getAllApplicationsThatHaveCandidate(String phoneNumber){
        return applicationDTOService.convertToDTO(applicationRepository.applicationsFromCandidate(phoneNumber));
    }

    public Application getApplication(ApplicationDTO applicationDto) {
        Long applicationId = applicationDto.getId();
        Application application = null;

        for (Application application1  : applicationRepository.applications()) {
            if (application1.identity().toString().equals(applicationId.toString())) {
                application =application1;
            }
        }
        return application;
    }
}
