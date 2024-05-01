package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.Application;
import jobs4u.base.entitymanagement.domain.CustomerCode;
import jobs4u.base.entitymanagement.domain.Entity;
import jobs4u.base.entitymanagement.repository.EntityRepository;

import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Consumer;

public class JpaEntityRepository extends JpaAutoTxRepository<Entity, CustomerCode, CustomerCode>
        implements EntityRepository {

    public JpaEntityRepository(final TransactionalContext autoTx) {
        super(autoTx, "customerCode");
    }

    public JpaEntityRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "customerCode");
    }

    @Override
    public List<Entity> getCustomersByUsername(Username username) {
        return null;
    }
}
