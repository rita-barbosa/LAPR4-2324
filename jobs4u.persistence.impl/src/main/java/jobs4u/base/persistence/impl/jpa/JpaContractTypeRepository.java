package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.Application;
import jobs4u.base.jobopeningmanagement.domain.ContractType;
import jobs4u.base.jobopeningmanagement.repositories.ContractTypeRepository;

import java.util.List;

public class JpaContractTypeRepository
        extends JpaAutoTxRepository<ContractType, String, String>
        implements ContractTypeRepository {


    public JpaContractTypeRepository(final TransactionalContext autoTx) {
        super(autoTx, "contractTypeDesignation");
    }

    public JpaContractTypeRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "contractTypeDesignation");
    }

    @Override
    public List<ContractType> contractTypes() {
        return null;
    }
}
