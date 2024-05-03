package jobs4u.base.interviewmodelmanagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.base.interviewmodelmanagement.domain.InterviewModelName;
import jobs4u.base.interviewmodelmanagement.domain.InterviewModel;

import java.util.List;
import java.util.Optional;

public interface InterviewModelRepository
        extends DomainRepository<InterviewModelName, InterviewModel> {

    List<InterviewModel> interviewModels();

    Optional<InterviewModel> getFileByName(String filename);
}
