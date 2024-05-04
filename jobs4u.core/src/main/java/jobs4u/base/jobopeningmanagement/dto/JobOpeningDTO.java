package jobs4u.base.jobopeningmanagement.dto;

import eapli.framework.validations.Preconditions;
import jobs4u.base.jobopeningmanagement.domain.*;

import java.util.Objects;

public class JobOpeningDTO {

    private final String function;
    private final String description;
    private final String status;
    private final String contractType;
    private final String workMode;
    private final String numVacancies;
    private final String requirementName;
    private final String customerCode;
    private final String jobReference;
    private final String address;


    public JobOpeningDTO(String address, String function, String description, String status, String contractType,
                         String workMode, String numVacancies, String requirementName,
                         String jobReference, String customerCode) {

        Preconditions.noneNull(address, function, description, status, contractType, workMode, numVacancies, requirementName, jobReference);

        this.function = function;
        this.description = description;
        this.status = status;
        this.contractType = contractType;
        this.workMode = workMode;
        this.numVacancies = numVacancies;
        this.requirementName = requirementName;
        this.customerCode = customerCode;
        this.jobReference = jobReference;
        this.address = address;
    }


    public String getFunction() {
        return function;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getContractType() {
        return contractType;
    }

    public String getWorkMode() {
        return workMode;
    }

    public String getNumVacancies() {
        return numVacancies;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getJobReference() {
        return jobReference;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobOpeningDTO)) return false;
        JobOpeningDTO that = (JobOpeningDTO) o;
        return Objects.equals(getFunction(), that.getFunction()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getContractType(), that.getContractType()) && Objects.equals(getWorkMode(), that.getWorkMode()) && Objects.equals(getNumVacancies(), that.getNumVacancies()) && Objects.equals(getRequirementName(), that.getRequirementName()) && Objects.equals(getCustomerCode(), that.getCustomerCode()) && Objects.equals(getJobReference(), that.getJobReference()) && Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFunction(), getDescription(), getStatus(), getContractType(), getWorkMode(), getNumVacancies(), getRequirementName(), getCustomerCode(), getJobReference(), getAddress());
    }
}
