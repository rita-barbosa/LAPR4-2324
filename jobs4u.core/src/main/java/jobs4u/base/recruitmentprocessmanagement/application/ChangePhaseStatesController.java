package jobs4u.base.recruitmentprocessmanagement.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jobs4u.base.customermanagement.application.CustomerManagementService;
import jobs4u.base.jobopeningmanagement.application.JobOpeningListDTOService;
import jobs4u.base.jobopeningmanagement.application.JobOpeningManagementService;
import jobs4u.base.jobopeningmanagement.domain.JobReference;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.recruitmentprocessmanagement.dto.RecruitmentProcessDTO;
import jobs4u.base.usermanagement.domain.BaseRoles;
import org.hibernate.mapping.Collection;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ChangePhaseStatesController {

    private final AuthorizationService authz;
    private final CustomerManagementService customerManagementService;
    private final JobOpeningManagementService jobOpeningManagementService;
    private final RecruitmentProcessManagementService recruitmentProcessManagementService;
    private final JobOpeningListDTOService jobOpeningListDTOService;

    public ChangePhaseStatesController() {
        this.authz = AuthzRegistry.authorizationService();
        this.customerManagementService = new CustomerManagementService();
        this.jobOpeningManagementService = new JobOpeningManagementService();
        this.jobOpeningListDTOService = new JobOpeningListDTOService();
        this.recruitmentProcessManagementService = new RecruitmentProcessManagementService();
    }
    public Iterable<JobOpeningDTO> getJobOpeningList() throws ClassNotFoundException {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER);
        Optional<SystemUser> user = authz.loggedinUserWithPermissions(BaseRoles.CUSTOMER_MANAGER);
        if (user.isPresent()) {
            return jobOpeningManagementService.plannedJobOpeningsOfCustomerManager(user.get().username());
        }else {
            return Collections.emptyList();
        }

    }

    public RecruitmentProcessDTO getRecruitmentProcessDTOWithJobReference(String jobReference) throws ClassNotFoundException {
        try {
            return recruitmentProcessManagementService.getRecruitmentProcessWithJobReference(new JobReference(jobReference));
        }catch(Exception e){
            System.out.println("[ No Job Openings Found Associated To This Customer Manager ]");
            throw new ClassNotFoundException("It was not possible to retrieve the job opening's data.");
        }
    }

    public boolean goBackAPhase(String jobReference) {
        try {
            recruitmentProcessManagementService.goBackAPhase(jobReference);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean goNextPhase(String jobReference) {
        try {
            recruitmentProcessManagementService.goNextPhase(jobReference);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
