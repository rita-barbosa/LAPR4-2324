package jobs4u.base.jobopeningmanagement.application;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.pubsub.EventPublisher;
import eapli.framework.infrastructure.pubsub.impl.inprocess.service.InProcessPubSub;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.candidatemanagement.domain.Candidate;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.JobReference;
import jobs4u.base.jobopeningmanagement.domain.events.ApplicationAcceptedEvent;
import jobs4u.base.jobopeningmanagement.domain.events.ApplicationStatusChangedEvent;
import jobs4u.base.jobopeningmanagement.domain.events.JobOpeningResultsPublishedEvent;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.rankmanagement.domain.Rank;
import jobs4u.base.rankmanagement.domain.RankOrder;
import jobs4u.base.rankmanagement.persistence.RankRepository;
import jobs4u.base.usermanagement.domain.BaseRoles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PublishResultJobOpeningController {
    private static final AuthorizationService authz = AuthzRegistry.authorizationService();
    private static final JobOpeningManagementService joSvc = new JobOpeningManagementService();
    private static final RankRepository rankRepository = PersistenceContext.repositories().ranks();
    private static final EventPublisher dispatcher = InProcessPubSub.publisher();
    private static final ApplicationRepository appRepo = PersistenceContext.repositories().applications();


    public Iterable<JobOpeningDTO> getJobOpeningsList() {
        Optional<SystemUser> customerManagerOpt = authz.loggedinUserWithPermissions(BaseRoles.CUSTOMER_MANAGER);

        if (customerManagerOpt.isPresent()) {
            return joSvc.jobOpeningsInResultListOfCustomerManager(customerManagerOpt.get().username());
        } else {
            throw new IllegalArgumentException("Couldn't retrieve the job openings.");
        }
    }

    public void publishResultForJobOpening(JobOpeningDTO jobOpeningDTO, String userPassword) {
        Optional<SystemUser> customerManagerOpt = authz.loggedinUserWithPermissions(BaseRoles.CUSTOMER_MANAGER);

        if (customerManagerOpt.isPresent()) {
            publishResults(jobOpeningDTO, customerManagerOpt.get().email(), userPassword);
        } else {
            throw new IllegalArgumentException("Couldn't retrieve logged in user.");
        }
    }

    private void publishResults(JobOpeningDTO jobOpeningDTO, EmailAddress customerManager, String userPassword) {
        Optional<Rank> r = rankRepository.ofIdentity(new JobReference(jobOpeningDTO.getJobReference()));

        if (r.isEmpty()) {
            throw new IllegalArgumentException("Coudln't obtain the rank for the selected job opening.");
        }

        Rank rank = r.get();
        final List<Candidate> acceptedCandidateList = new ArrayList<>();
        String newStatus;

        for (RankOrder entry : rank.rankEntries()) {
            if (entry.numberRanked() <= jobOpeningDTO.getNumVacancies()) {
                Application app = entry.acceptApplication();
                newStatus = app.applicationStatus().getStatusDescription();
                acceptedCandidateList.add(app.candidate());
                appRepo.save(app);
                dispatcher.publish(new ApplicationAcceptedEvent(customerManager, entry.candidateEmail(), rank.identity(), userPassword));
            } else {
                Application app = entry.rejectApplication();
                newStatus = app.applicationStatus().getStatusDescription();
                appRepo.save(app);
            }
            dispatcher.publish(new ApplicationStatusChangedEvent(entry.candidateEmail(), rank.identity(), newStatus));
        }
        dispatcher.publish(new JobOpeningResultsPublishedEvent(customerManager, acceptedCandidateList, jobOpeningDTO.getCustomerCode(), rank.identity(), userPassword));
    }

}
