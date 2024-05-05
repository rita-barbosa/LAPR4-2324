package jobs4u.base.recruitmentprocessmanagement.dto;

import eapli.framework.time.domain.model.DateInterval;
import eapli.framework.validations.Preconditions;

import java.util.Calendar;
import java.util.Objects;

public class PhaseDTO {

    private String phaseType;

    private String description;

    private String status;

    private DateInterval period;

    public PhaseDTO(String phaseType, String description, String status, DateInterval period){
        Preconditions.noneNull(phaseType,description,period,status);
        Preconditions.nonEmpty(phaseType);
        Preconditions.nonEmpty(description);
        Preconditions.nonEmpty(status);

        this.description = description;
        this.phaseType = phaseType;
        this.status = status;
        this.period = period;
    }

    public String getPhaseType() {
        return phaseType;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Calendar getInitialDate() {
        return period.start();
    }

    public Calendar getFinalDate() {
        return period.end();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhaseDTO phaseDTO = (PhaseDTO) o;
        return Objects.equals(phaseType, phaseDTO.phaseType) && Objects.equals(description, phaseDTO.description) && Objects.equals(status, phaseDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phaseType, description, status);
    }
}
