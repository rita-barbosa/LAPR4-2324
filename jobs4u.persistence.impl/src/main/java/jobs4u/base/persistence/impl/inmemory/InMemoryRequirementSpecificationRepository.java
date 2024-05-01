package jobs4u.base.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import jobs4u.base.requirementsmanagement.domain.RequirementName;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.requirementsmanagement.repositories.RequirementSpecificationRepository;

import java.util.List;

public class InMemoryRequirementSpecificationRepository
        extends InMemoryDomainRepository<RequirementSpecification, RequirementName>
        implements RequirementSpecificationRepository {
    @Override
    public List<RequirementSpecification> getCustomerRequirementsSpecificationsFileList(String costumerCode) {
        return null;
    }

    @Override
    public RequirementSpecification getFileByName(String filename) {
        return null;
    }
}
