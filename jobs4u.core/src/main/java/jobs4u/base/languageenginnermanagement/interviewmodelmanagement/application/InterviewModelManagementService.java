package jobs4u.base.languageenginnermanagement.interviewmodelmanagement.application;

import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.domain.InterviewModel;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.domain.InterviewModelName;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.repositories.InterviewModelRepository;

public class InterviewModelManagementService {
    private static final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModels();

    public static void registerInterviewPlugin(String nameOfInterviewPlugin, String descriptionOfInterviewPlugin, String fullClassName){
        InterviewModel interviewModel = new InterviewModel(nameOfInterviewPlugin, descriptionOfInterviewPlugin, fullClassName);
        interviewModelRepository.save(interviewModel);
    }

    public boolean checkIfInterviewModelAlreadyExists(String identifier){
        return interviewModelRepository.containsOfIdentity(new InterviewModelName(identifier));
    }

}
