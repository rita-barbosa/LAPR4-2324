package jobs4u.base.applicationmanagement.dto;

import jobs4u.base.applicationmanagement.domain.ApplicationFile;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


public class ApplicationDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Long id;
    private final Set<ApplicationFile> files;
    private final Date applicationDate;
    private final String applicationStatus;
    private final String candidate;

    public ApplicationDTO(Long id, Set<ApplicationFile> files, Date applicationDate, String applicationStatus, String candidate) {
        this.id = id;
        this.files = files;
        this.applicationDate = applicationDate;
        this.applicationStatus = applicationStatus;
        this.candidate = candidate;
    }


    @Override
    public String toString() {
        return String.format("\n=====================================================================\n" +
                        "#Application: %d\n" +
                        "#File: %s\n" +
                        "#Application Date: %s\n" +
                        "#Application Status: %s\n" +
                        "#Candidate username: %s\n" +
                        "=====================================================================\n",
                id, files, applicationDate,
                applicationStatus, candidate);
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
