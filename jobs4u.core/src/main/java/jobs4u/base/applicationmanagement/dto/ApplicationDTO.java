package jobs4u.base.applicationmanagement.dto;

import jobs4u.base.candidatemanagement.domain.Candidate;

import java.io.File;
import java.util.Date;


public class ApplicationDTO {

    private Long id;
    private String requirementAnswer;
    private Boolean requirementResult;
    private File file;
    private Date applicationDate;
    private String applicationStatus;
//    private Candidate candidate;

    public ApplicationDTO(Long id, String requirementAnswer, Boolean requirementResult, File file, Date applicationDate, String applicationStatus) {
        this.id = id;
        this.requirementAnswer = requirementAnswer;
        this.requirementResult = requirementResult;
        this.file = file;
        this.applicationDate = applicationDate;
        this.applicationStatus = applicationStatus;
//        this.candidate = candidate;
    }


    @Override
    public String toString() {
        return String.format("\n=====================================================================\n" +
                "#Application: %d\n" +
                        "#Requirement Answer: %s\n" +
                        "#Requirement Result: %s\n" +
                        "#File: %s\n" +
                        "#Application Date: %s\n" +
                        "#Application Status: %s\n" +
                        "=====================================================================\n",
                id, requirementAnswer, requirementResult, file, applicationDate,
                applicationStatus);
    }

    public String getRequirementAnswer() {
        return requirementAnswer;
    }

    public Boolean getRequirementResult() {
        return requirementResult;
    }

    public File getFile() {
        return file;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public Long getId() {
        return id;
    }
}
