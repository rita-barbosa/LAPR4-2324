package jobs4u.base.entitymanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.entitymanagement.domain.CustomerCode;
import jobs4u.base.entitymanagement.domain.Customer;

import java.util.List;

public interface CustomerRepository extends DomainRepository<CustomerCode, Customer> {

   List<Customer> getCustomersByUsername(Username username);
}
