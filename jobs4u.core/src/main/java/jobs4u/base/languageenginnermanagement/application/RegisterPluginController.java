package jobs4u.base.languageenginnermanagement.application;

import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.application.InterviewModelManagementService;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.application.RequirementSpecificationManagementService;

public class RegisterPluginController {

    private final static InterviewModelManagementService interviewModelManagementService = new InterviewModelManagementService();

    private final static RequirementSpecificationManagementService requirementSpecificationManagementService = new RequirementSpecificationManagementService();

    public void registerInterviewPlugin(String nameOfInterviewPlugin, String descriptionOfInterviewPlugin, String pathOfInterviewPlugin){
        interviewModelManagementService.registerInterviewPlugin(nameOfInterviewPlugin, descriptionOfInterviewPlugin, pathOfInterviewPlugin);
    }

    public void registerRequirementPlugin(String nameOfInterviewPlugin, String descriptionOfInterviewPlugin, String pathOfInterviewPlugin){
        requirementSpecificationManagementService.registerRequirementPlugin(nameOfInterviewPlugin, descriptionOfInterviewPlugin, pathOfInterviewPlugin);
    }

    public boolean checkIfInterviewModelAlreadyExists(String identifier){
        return interviewModelManagementService.checkIfInterviewModelAlreadyExists(identifier);
    }

    public boolean checkIfRequirementSpecificationAlreadyExists(String identifier){
        return requirementSpecificationManagementService.checkIfRequirementSpecificationAlreadyExists(identifier);
    }

}
