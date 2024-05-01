package jobs4u.base.jobopeningmanagement.application;

import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.entitymanagement.dto.CustomerDTO;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.domain.JobReference;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;

public class JobOpeningManagementService {

    private final JobOpeningRepository jobOpeningRepository = PersistenceContext
            .repositories().jobOpenings();

    public JobOpening registerJobOpening(String function, ContractTypeDTO contractTypeDenomination,
                                         WorkModeDTO workModeDenomination, String streetName, String city,
                                         String district, String state, String zipcode, int numVacancies,
                                         String description, RequirementSpecification requirementsFile,
                                         CustomerDTO companyInfo){

        JobReference lastReference = jobOpeningRepository.lastJobReference(companyInfo.costumerCode());

        return new JobOpening(function, contractTypeDenomination, workModeDenomination, streetName, city, district, state,
                zipcode, numVacancies, description, requirementsFile, lastReference);
    }



}
