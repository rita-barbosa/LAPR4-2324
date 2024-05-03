package jobs4u.base.jobopeningmanagement.dto;

import eapli.framework.representations.dto.DTO;
import jobs4u.base.customermanagement.domain.CustomerCode;
import lombok.AllArgsConstructor;
import lombok.Data;


public class JobOpeningDTO {

    private String function;

    private String contractType;

    private String workMode;

    private String jobReference;

    private String address;

    private String description;

    private Integer numVacancies;

    private String company;

    public JobOpeningDTO(String function, String contractType, String workMode, String jobReference, String address, String description, Integer numVacancies, String company) {
        this.function = function;
        this.contractType = contractType;
        this.workMode = workMode;
        this.jobReference = jobReference;
        this.address = address;
        this.description = description;
        this.numVacancies = numVacancies;
        this.company = company;
    }

    @Override
    public String toString() {
        return String.format("»» Job Reference: %s\n" +
                        " » Function: %s |" +
                        " Contract Type: %s |" +
                        " Work Mode: %s\n" +
                        " » Address: %s\n" +
                        " » Description: %s\n" +
                        " » Number of Vacancies: %d\n" +
                        " » Company: %s\n",
                jobReference, function, contractType, workMode, address,
                description, numVacancies, company);
    }
}
