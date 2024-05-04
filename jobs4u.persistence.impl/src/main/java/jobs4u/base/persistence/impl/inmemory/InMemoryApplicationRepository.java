package jobs4u.base.persistence.impl.inmemory;


import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryApplicationRepository
        extends InMemoryDomainRepository<Application, Long>
        implements ApplicationRepository  {

    static {
        InMemoryInitializer.init();
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
