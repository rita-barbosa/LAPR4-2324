package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.Application;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.domain.JobReference;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;

import java.util.NoSuchElementException;

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
}
