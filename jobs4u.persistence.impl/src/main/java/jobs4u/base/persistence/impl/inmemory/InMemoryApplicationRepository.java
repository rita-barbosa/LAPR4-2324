package jobs4u.base.persistence.impl.inmemory;

import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;

import java.util.List;

public class InMemoryApplicationRepository implements ApplicationRepository {

    @Override
    public List<Application> applications() {
        return null;
    }
}
