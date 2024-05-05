package jobs4u.base.applicationmanagement.dto;

import jobs4u.base.applicationmanagement.domain.ApplicationFile;
import jobs4u.base.candidatemanagement.domain.Candidate;

import java.io.File;
import java.util.Date;
import java.util.Set;


public class ApplicationDTO {

    private Long id;
    private String requirementAnswer;
    private Boolean requirementResult;
    private Set<ApplicationFile> files;
    private Date applicationDate;
    private String applicationStatus;
    private String candidate;

    public ApplicationDTO(Long id, String requirementAnswer, Boolean requirementResult, Set<ApplicationFile> files, Date applicationDate, String applicationStatus, String candidate) {
        this.id = id;
        this.requirementAnswer = requirementAnswer;
        this.requirementResult = requirementResult;
        this.files = files;
        this.applicationDate = applicationDate;
        this.applicationStatus = applicationStatus;
        this.candidate = candidate;
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
                        "#Candidate username: %s\n" +
                        "=====================================================================\n",
                id, requirementAnswer, requirementResult, files, applicationDate,
                applicationStatus, candidate);
    }

    public String getRequirementAnswer() {
        return requirementAnswer;
    }

    public Boolean getRequirementResult() {
        return requirementResult;
    }

    public Set<ApplicationFile> getApplicationFiles() {
        return files;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public String getCandidate(){
        return candidate;
    }

    public Long getId() {
        return id;
    }
}
