package jobs4u.base.persistence.impl.inmemory;

import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.customermanagement.domain.Customer;
import jobs4u.base.customermanagement.domain.CustomerCode;
import jobs4u.base.customermanagement.repository.CustomerRepository;

import java.util.List;

public class InMemoryCustomerRepository
        extends InMemoryDomainRepository<Customer, CustomerCode>
        implements CustomerRepository {
    @Override
    public List<Customer> getCustomersByUsername(Username username) {
        throw new UnsupportedOperationException("No yet implemented.");
    }
}
