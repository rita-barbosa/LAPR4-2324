package jobs4u.base.interviewmodelmanagement.application;

import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.interviewmodelmanagement.dto.InterviewModelDTO;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.domain.InterviewModel;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.repositories.InterviewModelRepository;


import java.util.List;

public class InterviewModelManagementService {

    private final InterviewModelRepository repository = PersistenceContext.repositories().interviewModels();

    private final InterviewModelDtoService dtoService = new InterviewModelDtoService();

    public Iterable<InterviewModelDTO> availableInterviewModels() {
        Iterable<InterviewModel> interviewModels = repository.findAll();

        return dtoService.toDto(interviewModels);
    }

}
