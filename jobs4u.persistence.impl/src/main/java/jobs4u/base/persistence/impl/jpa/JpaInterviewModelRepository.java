package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.Application;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.domain.InterviewModel;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.domain.InterviewModelName;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.repositories.InterviewModelRepository;
import org.hibernate.HibernateException;

import java.util.List;
import java.util.Optional;

public class JpaInterviewModelRepository
        extends JpaAutoTxRepository<InterviewModel, InterviewModelName, InterviewModelName>
        implements InterviewModelRepository {


    public JpaInterviewModelRepository(final TransactionalContext autoTx) {
        super(autoTx, "interviewModelName");
    }

    public JpaInterviewModelRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "interviewModelName");
    }

    @Override
    public List<InterviewModel> interviewModels() {
        return null;
    }

    @Override
    public Optional<InterviewModel> getFileByName(String filename) {
        return Optional.empty();
    }

}
