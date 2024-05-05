package jobs4u.base.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.framework.time.domain.model.DateInterval;
import jobs4u.base.customermanagement.domain.Customer;
import jobs4u.base.customermanagement.domain.CustomerCode;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.domain.JobOpeningStatus;
import jobs4u.base.jobopeningmanagement.domain.JobOpeningStatusEnum;
import jobs4u.base.jobopeningmanagement.domain.JobReference;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class InMemoryJobOpeningRepository
        extends InMemoryDomainRepository<JobOpening, JobReference>
        implements JobOpeningRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public JobReference lastJobReference(String customerCode) {
        int repoSize = (int) size();
        JobReference lastJobReference = null;
        if (repoSize == 0){
            System.out.println("First job opening being registered in the system!");
            return new JobReference(customerCode, 0);
        }
        Iterable<JobOpening> jobOpenings = findAll();
        for (JobOpening element : jobOpenings) {
            lastJobReference = element.identity();
        }
        if (lastJobReference == null) {
            throw new NoSuchElementException("It was not possible to retrieve the last registered job opening.");
        }
        return lastJobReference;
    }

    @Override
    public List<JobOpening> getJobOpeningListMatchingCustomerCodesList(Set<CustomerCode> customerCodes) {
        List<JobOpening> jobOpeningArrayList = new ArrayList<>();
        for (CustomerCode code : customerCodes){
            Iterable<JobOpening> jobOpenings = match(e -> e.jobReference().getcustomerCode().equals(code.toString()));
            for (JobOpening element : jobOpenings) {
                jobOpeningArrayList.add(element);
            }
        }
        return jobOpeningArrayList;
    }

    @Override
    public List<JobOpening> getJobOpeningListMatchingCustomer(Customer customer) {
        List<JobOpening> jobOpeningArrayList = new ArrayList<>();

        Iterable<JobOpening> jobOpenings = match(e -> {
            e.jobReference().getcustomerCode();
            customer.customerCode();
            return false;
        });
        for (JobOpening element : jobOpenings) {
            jobOpeningArrayList.add(element);
        }
        return jobOpeningArrayList;
    }

    @Override
    public List<JobOpening> getJobOpeningListMatchingStatus(String started) {
        List<JobOpening> jobOpeningArrayList = new ArrayList<>();
        Iterable<JobOpening> jobOpenings = match(e -> e.getStatus().toString().equals(started));
        for (JobOpening element : jobOpenings) {
            jobOpeningArrayList.add(element);
        }
        return jobOpeningArrayList;
    }

    @Override
    public List<JobOpening> getJobOpeningListWithinDateInterval(DateInterval interval) {
        List<JobOpening> jobOpeningArrayList = new ArrayList<>();
        Iterable<JobOpening> jobOpenings = match(e -> e.getRecruitmentProcess().getRecruitmentPeriod().getRecruitmentInterval().start().compareTo(interval.start()) >= 0
                || e.getRecruitmentProcess().getRecruitmentPeriod().getRecruitmentInterval().end().compareTo(interval.end()) <= 0);
        for (JobOpening element : jobOpenings) {
            jobOpeningArrayList.add(element);
        }
        return jobOpeningArrayList;
    }

    @Override
    public Iterable<JobOpening> getUNFINISHEDJobOpeningList() {
        String status = new JobOpeningStatus(JobOpeningStatusEnum.UNFINISHED).getStatusDescription();
        List<JobOpening> jobOpeningArrayList = new ArrayList<>();
        Iterable<JobOpening> jobOpenings = match(e -> e.getStatus().toString().equals(status));
        for (JobOpening element : jobOpenings) {
            jobOpeningArrayList.add(element);
        }
        return jobOpeningArrayList;
    }

    @Override
    public Iterable<JobOpening> findAllJobOpeningsNotStarted() {
        return match(e -> e.jobOpeningStatus().getStatusDescription().equals(String.valueOf(JobOpeningStatusEnum.UNFINISHED))
                || e.jobOpeningStatus().getStatusDescription().equals(String.valueOf(JobOpeningStatusEnum.NOT_STARTED)));
    }

}
