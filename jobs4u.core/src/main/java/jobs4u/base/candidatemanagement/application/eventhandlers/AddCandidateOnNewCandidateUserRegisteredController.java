package jobs4u.base.candidatemanagement.application.eventhandlers;

import jobs4u.base.candidatemanagement.domain.CandidateUser;
import jobs4u.base.candidatemanagement.domain.events.NewCandidateUserRegisteredEvent;
import jobs4u.base.candidatemanagement.repository.CandidateRepository;
import jobs4u.base.infrastructure.persistence.PersistenceContext;

class AddCandidateOnNewCandidateUserRegisteredController {
    private final CandidateRepository candidateRepository = PersistenceContext.repositories().candidates();

    public void registerNewCandidate(NewCandidateUserRegisteredEvent event) {
        candidateRepository.save(new CandidateUser(event.systemUser(), event.phoneNumber()));
    }


}
