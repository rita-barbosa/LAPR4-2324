package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.Application;
import jobs4u.base.recruitmentprocessmanagement.domain.RecruitmentProcess;
import jobs4u.base.recruitmentprocessmanagement.repository.RecruitmentProcessRepository;


public class JpaRecruitmentProcessRepository
        extends JpaAutoTxRepository<RecruitmentProcess, Long, Long>
        implements RecruitmentProcessRepository {

    public JpaRecruitmentProcessRepository(final TransactionalContext autoTx) {
        super(autoTx, "recruitmentID");
    }

    public JpaRecruitmentProcessRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "recruitmentID");
    }
}
