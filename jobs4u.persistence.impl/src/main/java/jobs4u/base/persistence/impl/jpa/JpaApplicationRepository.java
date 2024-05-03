package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;


import java.util.List;
import java.util.Map;
public class JpaApplicationRepository implements ApplicationRepository {
//    public JpaApplicationRepository(String persistenceUnitName, String identityFieldName) {
//        super(persistenceUnitName, identityFieldName);
//    }
//
//    public JpaApplicationRepository(String persistenceUnitName, Map properties, String identityFieldName) {
//        super(persistenceUnitName, properties, identityFieldName);
//    }
//
//    public JpaApplicationRepository(TransactionalContext tx, String identityFieldName) {
//        super(tx, identityFieldName);
//    }

    @Override
    public List<Application> applications() {
        return null;
    }
}
