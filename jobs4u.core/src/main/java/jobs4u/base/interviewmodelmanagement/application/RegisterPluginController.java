package jobs4u.base.interviewmodelmanagement.application;

import jobs4u.base.requirementsmanagement.application.RequirementSpecificationManagementService;

public class RegisterPluginController {

    private final static InterviewModelManagementService interviewModelManagementService = new InterviewModelManagementService();

    private final static RequirementSpecificationManagementService requirementSpecificationManagementService = new RequirementSpecificationManagementService();

    public void registerInterviewPlugin(String nameOfInterviewPlugin, String descriptionOfInterviewPlugin, String pathOfInterviewPlugin, String configFile) {
        interviewModelManagementService.registerInterviewPlugin(nameOfInterviewPlugin, descriptionOfInterviewPlugin, pathOfInterviewPlugin,configFile);
    }

    public void registerRequirementPlugin(String nameOfInterviewPlugin, String descriptionOfInterviewPlugin, String pathOfInterviewPlugin, String configFile) {
        requirementSpecificationManagementService.registerRequirementPlugin(nameOfInterviewPlugin, descriptionOfInterviewPlugin, pathOfInterviewPlugin, configFile);
    }

    public boolean checkIfInterviewModelAlreadyExists(String identifier) {
        return interviewModelManagementService.checkIfInterviewModelAlreadyExists(identifier);
    }

    public boolean checkIfRequirementSpecificationAlreadyExists(String identifier) {
        return requirementSpecificationManagementService.checkIfRequirementSpecificationAlreadyExists(identifier);
    }

}
