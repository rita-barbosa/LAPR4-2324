package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.framework.time.domain.model.DateInterval;
import jakarta.persistence.TemporalType;
import jakarta.persistence.TypedQuery;
import jobs4u.base.Application;
import jobs4u.base.customermanagement.domain.Customer;
import jobs4u.base.customermanagement.domain.CustomerCode;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.domain.JobReference;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;
import org.hibernate.HibernateException;


import java.util.*;

public class JpaJobOpeningRepository
        extends JpaAutoTxRepository<JobOpening, JobReference, JobReference>
        implements JobOpeningRepository {


    public JpaJobOpeningRepository(final TransactionalContext autoTx) {
        super(autoTx, "jobReference");
    }

    public JpaJobOpeningRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "jobReference");
    }

    @Override
    public Iterable<JobOpening> findAllJobOpeningsNotStarted() {
        return match("e.status.statusDescription = 'UNFINISHED' OR e.status.statusDescription ='NOT_STARTED'");
    }

    @Override
    public JobReference lastJobReference(String customerCode) {
        int size = (int) size();
        JobReference lastJobReference = null;
        if (size == 0){
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
        List<JobOpening> jobOpenings = new ArrayList<>();
        for (CustomerCode code : customerCodes){
            try{
                jobOpenings.addAll(match("e.jobReference.companyCode = :code","code", code.costumerCode()));
            }catch (HibernateException ex){
                jobOpenings.addAll(match("e=(SELECT c FROM JobOpening c WHERE c.jobReference.companyCode=:code)",
                        "code", code.costumerCode()));
            }
        }
        return jobOpenings;
    }

    @Override
    public List<JobOpening> getJobOpeningListMatchingCustomer(Customer customer) {
        try{
            return match("e.jobReference.companyCode = :code", "code", customer.customerCode().toString());
        }catch (HibernateException ex){
            return match("e=(SELECT c FROM JobOpening c WHERE c.jobReference.companyCode=:code)",
                    "code", customer.customerCode().costumerCode());
        }
    }

    @Override
    public List<JobOpening> getJobOpeningListMatchingStatus(String started) {
        try{
            return match("e.status.statusDescription = :status", "status", started);
        }catch (HibernateException ex){
            return match("e=(SELECT c FROM JobOpening c WHERE c.status.statusDescription = :status)",
                    "status", started);
        }
    }

    public List<Calendar> getStartDateRecruitmentList(){
        final TypedQuery<Calendar> q = createQuery(
                "SELECT e FROM JobOpening e WHERE e.recruitmentProcess.recruitmentPeriod.recruitmentInterval.dateStart <= :start AND e.recruitmentProcess.recruitmentPeriod.recruitmentInterval.dateEnd >= :end",
                Calendar.class);
        return q.getResultList();
    }


    @Override
    public List<JobOpening> getJobOpeningListWithinDateInterval(DateInterval interval) {

        final TypedQuery<JobOpening> q = createQuery(
                "SELECT e FROM JobOpening e WHERE e.recruitmentProcess.recruitmentPeriod.recruitmentInterval.dateStart <= :start AND e.recruitmentProcess.recruitmentPeriod.recruitmentInterval.dateEnd >= :end",
                JobOpening.class);
        q.setParameter("start", interval.start(), TemporalType.DATE);
        q.setParameter("end", interval.end(), TemporalType.DATE);
        return q.getResultList();


//        final Map<String, Object> params = new HashMap<>();
//        params.put("start", interval.start());
//        params.put("end", interval.start());
//        return match("e.recruitmentProcess.recruitmentPeriod.recruitmentInterval.dateStart >= :start" +
//                "AND e.recruitmentProcess.recruitmentPeriod.recruitmentInterval.dateEnd <= :end", params);
    }


}
