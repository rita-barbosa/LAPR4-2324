package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.Application;
import jobs4u.base.customermanagement.domain.Customer;
import jobs4u.base.customermanagement.domain.CustomerCode;
import jobs4u.base.customermanagement.repository.CustomerRepository;


import java.util.ArrayList;
import java.util.List;

public class JpaCustomerRepository extends JpaAutoTxRepository<Customer, Long, CustomerCode>
        implements CustomerRepository {

    public JpaCustomerRepository(final TransactionalContext autoTx) {
        super(autoTx, "code");
    }

    public JpaCustomerRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "code");
    }

    @Override
    public List<Customer> getCustomersByUsername(Username username) {
        List<Customer> assignedCustomers = new ArrayList<>();
        Iterable<Customer> entities = match("e=(SELECT c FROM Customer c WHERE c.customerManager.username=:name)", "name", username);

        for (Customer Customer : entities) {
            assignedCustomers.add(Customer);
        }
        return assignedCustomers;
    }
}
