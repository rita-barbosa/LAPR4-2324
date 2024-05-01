package jobs4u.base.jobopeningmanagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.domain.JobReference;
import jobs4u.base.jobopeningmanagement.domain.WorkMode;

import java.util.List;

public interface WorkModeRepository
        extends DomainRepository<String, WorkMode> {

    List<WorkMode> workModes();
}
