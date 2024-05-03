package jobs4u.base.jobopeningmanagement.dto;

import eapli.framework.representations.dto.DTO;
import jobs4u.base.customermanagement.domain.CustomerCode;
import lombok.AllArgsConstructor;
import lombok.Data;


@DTO
@Data
@AllArgsConstructor
public class JobOpeningDTO {

    private String function;

    private String contractType;

    private String workMode;

    private String status;

    private String jobReference;

    private String address;

    private String description;

    private Integer numVacancies;

    private String company;
    private String customerCode;


//    public JobOpeningDTO(String function, String contractType, String workMode, String status, String jobReference, String address, String description, Integer numVacancies, String company, CustomerCode customerCode) {
//        this.function = function;
//        this.contractType = contractType;
//        this.workMode = workMode;
//        this.status = status;
//        this.jobReference = jobReference;
//        this.address = address;
//        this.description = description;
//        this.numVacancies = numVacancies;
//        this.company = company;
//        this.customerCode = customerCode;
//    }


    @Override
    public String toString() {
        return String.format("#Job Reference: %s\n" +
                        "#Function: %s\n" +
                        "#Contract Type: %s\n" +
                        "#Work Mode: %s\n" +
                        "#Status: %s\n" +
                        "#Address: %s\n" +
                        "#Description: %s\n" +
                        "#Number of Vacancies: %d\n" +
                        "#Company: %s\n" +
                        "#Customer Code: %s",
                jobReference, function, contractType, workMode, status, address,
                description, numVacancies, company, customerCode);
    }
}
