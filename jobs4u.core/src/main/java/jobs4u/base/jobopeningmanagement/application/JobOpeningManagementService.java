package jobs4u.base.jobopeningmanagement.application;

import eapli.framework.time.domain.model.DateInterval;
import jobs4u.base.customermanagement.application.CustomerManagementService;
import jobs4u.base.customermanagement.domain.Customer;
import jobs4u.base.customermanagement.domain.CustomerCode;
import jobs4u.base.customermanagement.dto.CustomerDTO;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.domain.JobReference;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;

import java.util.*;

public class JobOpeningManagementService {

    private final JobOpeningRepository jobOpeningRepository = PersistenceContext
            .repositories().jobOpenings();

    private final JobOpeningDTOService dtoSvc = new JobOpeningDTOService();

    private final CustomerManagementService customerManagementService = new CustomerManagementService();

    public JobOpening registerJobOpening(String function, ContractTypeDTO contractTypeDenomination,
                                         WorkModeDTO workModeDenomination, String streetName, String city,
                                         String district, String state, String zipcode, int numVacancies,
                                         String description, RequirementSpecification requirementsFile,
                                         CustomerDTO companyInfo){

        JobReference lastReference = jobOpeningRepository.lastJobReference(companyInfo.customerCode());

        JobOpening jobOpening = new JobOpening(function, contractTypeDenomination, workModeDenomination, streetName, city,
                district, state, zipcode, numVacancies, description, requirementsFile, lastReference);

        jobOpeningRepository.save(jobOpening);
        return jobOpening;
    }

    public Iterable<JobOpeningDTO> acticeJobOpenings() {
        return dtoSvc.convertToDTO(jobOpeningRepository.findAllJobOpeningsNotStarted());
    }


    public List<JobOpening> getJobOpeningsFromCustomerCodes(List<CustomerDTO> customerDTOList) {
        Set<CustomerCode> customerCodes = new HashSet<>();
        for (CustomerDTO customerDTO : customerDTOList){
            customerCodes.add(new CustomerCode(customerDTO.customerCode()));
        }
       return jobOpeningRepository.getJobOpeningListMatchingCustomerCodesList(customerCodes);
    }

    public List<JobOpening> filterJobOpeningListByCompanyName(CustomerDTO dto) {
        Optional<Customer> customer = customerManagementService.getCustomerByDTO(dto);
        if (customer.isPresent()){
            return jobOpeningRepository.getJobOpeningListMatchingCustomer(customer.get());
        }
        throw new NoSuchElementException("Failure - filterJobOpeningListByCompanyName");
    }

    public List<JobOpening> filterJobOpeningListByCustomerCode(String customerCode) {
        Optional<Customer> customer = customerManagementService.getCustomerByCustomerCode(customerCode);
        if (customer.isPresent()){
            return jobOpeningRepository.getJobOpeningListMatchingCustomer(customer.get());
        }
        throw new NoSuchElementException("Unable to retrieve the customer with customer code " + customerCode);
    }

    public List<JobOpening> filterJobOpeningListBySTARTEDStatus(String started, List<JobOpening> virgemJobOpeningList) {
        List<JobOpening> filtered = jobOpeningRepository.getJobOpeningListMatchingStatus(started);
        virgemJobOpeningList.removeIf(jobOpening -> !filtered.contains(jobOpening));
        return virgemJobOpeningList;
    }

    public List<JobOpening> filterJobOpeningListByDateInterval(DateInterval interval, List<JobOpening> jobOpenings) {
        List<JobOpening> filtered = jobOpeningRepository.getJobOpeningListWithinDateInterval(interval);
        jobOpenings.removeIf(jobOpening -> !filtered.contains(jobOpening));
        if (jobOpenings.isEmpty()){
            throw new NoSuchElementException("There are no job openings in the defined interval.");
        }
        return jobOpenings;
    }
}
