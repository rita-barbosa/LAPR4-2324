package jobs4u.base.interviewmodelmanagement.application;

import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.interviewmodelmanagement.domain.InterviewModelName;
import jobs4u.base.interviewmodelmanagement.dto.InterviewModelDTO;
import jobs4u.base.interviewmodelmanagement.domain.InterviewModel;
import jobs4u.base.interviewmodelmanagement.repositories.InterviewModelRepository;

public class InterviewModelManagementService {

    private static final InterviewModelRepository repository = PersistenceContext.repositories().interviewModels();

    private final InterviewModelDtoService dtoService = new InterviewModelDtoService();

    public Iterable<InterviewModelDTO> allInterviewModels() {
         return dtoService.toDto(repository.findAll());
    }

    public static void registerInterviewPlugin(String nameOfInterviewPlugin, String descriptionOfInterviewPlugin, String fullClassName, String configFile,String dataImporter){
        InterviewModel interviewModel = new InterviewModel(nameOfInterviewPlugin, descriptionOfInterviewPlugin, fullClassName,configFile,dataImporter);
        repository.save(interviewModel);
    }

    public boolean checkIfInterviewModelAlreadyExists(String identifier){
        return repository.containsOfIdentity(new InterviewModelName(identifier));
    }

}
