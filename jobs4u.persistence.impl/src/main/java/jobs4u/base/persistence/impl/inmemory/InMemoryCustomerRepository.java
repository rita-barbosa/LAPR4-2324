package jobs4u.base.persistence.impl.inmemory;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.customermanagement.domain.Customer;
import jobs4u.base.customermanagement.domain.CustomerCode;
import jobs4u.base.customermanagement.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryCustomerRepository
        extends InMemoryDomainRepository<Customer, CustomerCode>
        implements CustomerRepository {

    @Override
    public List<Customer> getCustomersByUsername(Username username) {
        List<Customer> assignedCustomers = new ArrayList<>();
        Iterable<Customer> entities = match(e -> e.customerManager().username().equals(username));

        for (Customer Customer : entities) {
            assignedCustomers.add(Customer);
        }
        return assignedCustomers;
    }
}
