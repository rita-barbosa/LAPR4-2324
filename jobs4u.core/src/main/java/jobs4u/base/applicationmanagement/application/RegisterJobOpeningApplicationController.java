package jobs4u.base.applicationmanagement.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.domain.ApplicationFile;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.candidatemanagement.application.RegisterCandidateController;
import jobs4u.base.candidatemanagement.domain.Candidate;
import jobs4u.base.candidatemanagement.domain.PhoneNumber;
import jobs4u.base.candidatemanagement.repository.CandidateRepository;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;
import jobs4u.base.usermanagement.domain.BaseRoles;

import java.io.File;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@UseCaseController
public class RegisterJobOpeningApplicationController {

    private final RegisterCandidateController controller = new RegisterCandidateController();

    private final AuthorizationService authz;

    private final CandidateRepository candidateRepository;

    private final ApplicationManagementService applicationManagementService;

    public RegisterJobOpeningApplicationController(){
        this.authz = AuthzRegistry.authorizationService();
        this.applicationManagementService = new ApplicationManagementService();
        this.candidateRepository = PersistenceContext.repositories().candidates();
    }

    public boolean registerCandidate(String name, String email, String extension, String number){
        return controller.registerCandidate(name, email, extension, number);
    }

    public Optional<Application> registerApplication(Set<ApplicationFile> files, Date applicationDate, PhoneNumber phone){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.OPERATOR);

        Optional<Candidate> opCandidate = candidateRepository.findByPhoneNumber(phone);

        if (opCandidate.isPresent()){
            Candidate candidate = opCandidate.get();
            return Optional.of(applicationManagementService.registerApplication(files,
                    applicationDate, candidate));
        } else {
            return Optional.of(applicationManagementService.registerApplicationWithoutCandidate(files, applicationDate));
        }
    }

}
