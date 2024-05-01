package jobs4u.base.entitymanagement.application.eventhandlers;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jobs4u.base.clientusermanagement.domain.ClientUserBuilder;
import jobs4u.base.entitymanagement.domain.Customer;
import jobs4u.base.entitymanagement.domain.events.NewCustomerUserRegisteredEvent;
import jobs4u.base.entitymanagement.repository.CustomerRepository;
import jobs4u.base.infrastructure.persistence.PersistenceContext;

import java.util.Optional;

class AddCustomerOnNewCustomerUserRegisteredController {
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    public void registerNewCustomer(NewCustomerUserRegisteredEvent event) {
        customerRepository.save(new Customer(event.companyName(), event.address(),
                event.customerCode(), event.customerManager(), event.customerUser()));
    }
}
