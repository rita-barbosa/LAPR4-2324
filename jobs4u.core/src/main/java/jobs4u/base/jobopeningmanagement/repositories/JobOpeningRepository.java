package jobs4u.base.jobopeningmanagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.time.domain.model.DateInterval;
import jobs4u.base.customermanagement.domain.Customer;
import jobs4u.base.customermanagement.domain.CustomerCode;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.domain.JobReference;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface JobOpeningRepository extends DomainRepository<JobReference, JobOpening> {

    public Iterable<JobOpening> findAllJobOpeningsNotStarted();

    public Iterable<JobOpening> findAllJobOpeningsWithoutRecruitmentProcess();

    JobReference lastJobReference(String customerCode);

    List<JobOpening> getJobOpeningListMatchingCustomerCodesList(Set<CustomerCode> customerCode);

    List<JobOpening> getJobOpeningListMatchingCustomer(Customer customer);

    List<JobOpening> getJobOpeningListMatchingStatus(String started);

    List<JobOpening> getJobOpeningListWithinDateInterval(DateInterval interval);

    Iterable<JobOpening> getUNFINISHEDJobOpeningList();
    Iterable<JobOpening> findAllJobOpeningsWithCustomerCode(String c);
    Optional<JobOpening> getJobOpeningByJobReference(JobReference id);
}
