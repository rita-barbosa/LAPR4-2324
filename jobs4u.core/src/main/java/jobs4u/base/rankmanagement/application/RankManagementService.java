package jobs4u.base.rankmanagement.application;

import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.rankmanagement.persistence.RankRepository;

public class RankManagementService {

    private final RankRepository rankRepository = PersistenceContext
            .repositories().ranks();


}
