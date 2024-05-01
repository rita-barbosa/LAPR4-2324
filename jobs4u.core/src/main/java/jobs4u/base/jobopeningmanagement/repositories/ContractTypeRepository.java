package jobs4u.base.jobopeningmanagement.repositories;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.entitymanagement.domain.CustomerCode;
import jobs4u.base.entitymanagement.domain.Entity;
import jobs4u.base.entitymanagement.repository.EntityRepository;
import jobs4u.base.jobopeningmanagement.domain.ContractType;

import java.util.List;

public interface ContractTypeRepository {

    List<ContractType> contractTypes();


}
