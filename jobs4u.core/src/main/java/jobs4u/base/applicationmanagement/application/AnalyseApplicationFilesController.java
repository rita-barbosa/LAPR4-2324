package jobs4u.base.applicationmanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.candidatemanagement.application.CandidateManagementService;
import jobs4u.base.candidatemanagement.dto.CandidateDTO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class AnalyseApplicationFilesController {

    AuthorizationService authorizationService;
    ApplicationManagementService applicationManagementService;
    ApplicationFilesThreadService applicationFilesThreadService;
    CandidateManagementService candidateManagementService;

    public AnalyseApplicationFilesController() {
        candidateManagementService = new CandidateManagementService();
        applicationFilesThreadService = new ApplicationFilesThreadService();
        authorizationService = AuthzRegistry.authorizationService();
        applicationManagementService = new ApplicationManagementService();
    }


    public Map<String, Pair<Integer, List<String>>> analyzeFilesFromApplication(ApplicationDTO application) {
        return applicationFilesThreadService.getTop20Words(applicationManagementService.getApplicationWithId(application.getId()).get().allFiles());
    }

    public Iterable<CandidateDTO> getAllCandidatesDTO() {
        return candidateManagementService.getCandidatesListDTO();
    }

    public Iterable<ApplicationDTO> getAllApplicationsDTOByCandidate(String candidatePhoneNumber) {
        return applicationManagementService.getAllApplicationsThatHaveCandidate(candidatePhoneNumber);
    }
}
