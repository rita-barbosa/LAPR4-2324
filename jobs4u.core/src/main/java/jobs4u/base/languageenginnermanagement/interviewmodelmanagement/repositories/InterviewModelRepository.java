package jobs4u.base.languageenginnermanagement.interviewmodelmanagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.domain.InterviewModel;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.domain.InterviewModelName;

import java.util.List;
import java.util.Optional;

public interface InterviewModelRepository extends DomainRepository<InterviewModelName, InterviewModel> {

    List<InterviewModel> interviewModels();

    Optional<InterviewModel> getFileByName(String filename);
}
