package jobs4u.base.jobopeningmanagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.base.jobopeningmanagement.domain.ContractType;

import java.util.List;

public interface ContractTypeRepository
        extends DomainRepository<String, ContractType> {

    List<ContractType> contractTypes();


}
