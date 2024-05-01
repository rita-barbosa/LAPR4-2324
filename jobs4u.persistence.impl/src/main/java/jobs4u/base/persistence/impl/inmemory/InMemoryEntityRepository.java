package jobs4u.base.persistence.impl.inmemory;

import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.entitymanagement.domain.CustomerCode;
import jobs4u.base.entitymanagement.domain.Entity;
import jobs4u.base.entitymanagement.repository.EntityRepository;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class InMemoryEntityRepository
        extends InMemoryDomainRepository<Entity, CustomerCode>
        implements EntityRepository {
    @Override
    public List<Entity> getCustomersByUsername(Username username) {
        throw new UnsupportedOperationException("No yet implemented.");
    }
}
