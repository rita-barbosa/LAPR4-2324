package jobs4u.base.applicationmanagement.dto;

import jobs4u.base.candidatemanagement.Candidate;
import java.io.File;
import java.util.Date;


public class ApplicationDTO {

    private Long id;
    private String requirementAnswer;
    private Boolean requirementResult;
    private File file;
    private Date applicationDate;
    private String applicationStatus;
    private Candidate candidate;

    public ApplicationDTO(Long id, String requirementAnswer, Boolean requirementResult, File file, Date applicationDate, String applicationStatus, Candidate candidate) {
        this.id = id;
        this.requirementAnswer = requirementAnswer;
        this.requirementResult = requirementResult;
        this.file = file;
        this.applicationDate = applicationDate;
        this.applicationStatus = applicationStatus;
        this.candidate = candidate;
    }


    @Override
    public String toString() {
        return String.format("#Application: %s\n" +
                        "#Requirement Answer: %s\n" +
                        "#Requirement Result: %s\n" +
                        "#File: %s\n" +
                        "#Application Date: %s\n" +
                        "#Application Status: %s\n" +
                        "#Candidate: %s\n",
                id, requirementAnswer, requirementResult, file, applicationDate,
                applicationStatus, candidate);
    }
}
