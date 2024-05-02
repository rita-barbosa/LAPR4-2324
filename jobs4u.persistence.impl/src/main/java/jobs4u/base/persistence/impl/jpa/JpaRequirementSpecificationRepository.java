package jobs4u.base.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import jobs4u.base.Application;
import jobs4u.base.requirementsmanagement.domain.RequirementName;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.requirementsmanagement.repositories.RequirementSpecificationRepository;

import java.util.*;

public class JpaRequirementSpecificationRepository
        extends JpaAutoTxRepository<RequirementSpecification, RequirementName, RequirementName>
        implements RequirementSpecificationRepository {


    public JpaRequirementSpecificationRepository(final TransactionalContext autoTx) {
        super(autoTx, "requirementName");
    }

    public JpaRequirementSpecificationRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "requirementName");
    }


    @Override
    public List<RequirementSpecification> requirementsSpecifications() {
        List<RequirementSpecification> requirementsList = new ArrayList<>();
        Iterable<RequirementSpecification> requirementsSpecifications = findAll();
        for (RequirementSpecification req : requirementsSpecifications) {
            requirementsList.add(req);
        }
        return requirementsList;
    }

    @Override
    public Optional<RequirementSpecification> getFileByName(String filename) {
        final Map<String, Object> params = new HashMap<>();
        params.put("filename", filename);
        return matchOne("e.requirementName.name=:filename", params);
    }
}
