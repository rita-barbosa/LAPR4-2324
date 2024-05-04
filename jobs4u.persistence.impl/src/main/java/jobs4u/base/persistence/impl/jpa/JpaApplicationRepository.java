package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.candidatemanagement.domain.CandidateUser;
import jobs4u.base.candidatemanagement.domain.PhoneNumber;


import java.util.ArrayList;
import java.util.List;


public class JpaApplicationRepository
        extends JpaAutoTxRepository<Application, Long, Long>
        implements ApplicationRepository {

    public JpaApplicationRepository(final TransactionalContext autoTx){
        super(autoTx, "id");
    }

    public JpaApplicationRepository(final String puname){
        super(puname, jobs4u.base.Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public Iterable<Application> applications() {
        List<Application> applicationList = new ArrayList<>();
        Iterable<Application> applications = findAll();
        for (Application app : applications) {
            applicationList.add(app);
        }
        return applicationList;

    }
}
