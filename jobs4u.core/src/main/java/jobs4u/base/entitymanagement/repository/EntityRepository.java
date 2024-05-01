package jobs4u.base.entitymanagement.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.entitymanagement.domain.CustomerCode;
import jobs4u.base.entitymanagement.domain.Entity;

import java.util.List;

public interface EntityRepository extends DomainRepository<CustomerCode, Entity> {

   List<Entity> getCustomersByUsername(Username username);
}
