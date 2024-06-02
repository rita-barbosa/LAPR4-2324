package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jakarta.persistence.TypedQuery;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;


import java.util.ArrayList;
import java.util.List;


public class JpaApplicationRepository
        extends JpaAutoTxRepository<Application, Long, Long>
        implements ApplicationRepository {

    public JpaApplicationRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaApplicationRepository(final String puname) {
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

    @Override
    public Iterable<Application> applicationsForJobOpeningWithRequirements(String jobReference) {
        String[] f = jobReference.split("-");
        final TypedQuery<Application>
                q = createQuery("SELECT a \n" +
                        "FROM Application a \n" +
                        "WHERE a.id IN (\n" +
                        "    SELECT app.id \n" +
                        "    FROM JobOpening jo \n" +
                        "    JOIN jo.applications app \n" +
                        "    WHERE jo.jobReference.companyCode = :companyCode \n" +
                        "    AND jo.jobReference.sequentialCode = :sequentialCode\n" +
                        ")\n" +
                        "AND a.requirementAnswer IS NOT NULL",
                Application.class);
        q.setParameter("companyCode", f[0]);
        q.setParameter("sequentialCode", f[1]);
        return q.getResultList();
    }

}
