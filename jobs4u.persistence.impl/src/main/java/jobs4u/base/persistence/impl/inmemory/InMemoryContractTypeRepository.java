package jobs4u.base.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.jobopeningmanagement.domain.ContractType;
import jobs4u.base.jobopeningmanagement.repositories.ContractTypeRepository;

import java.util.List;

public class InMemoryContractTypeRepository
        extends InMemoryDomainRepository<ContractType, String>
        implements ContractTypeRepository {

    @Override
    public List<ContractType> contractTypes() {
        return null;
    }
}
