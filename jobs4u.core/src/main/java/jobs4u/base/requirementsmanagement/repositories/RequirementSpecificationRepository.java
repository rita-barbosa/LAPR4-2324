package jobs4u.base.requirementsmanagement.repositories;

import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;

import java.util.List;

public interface RequirementSpecificationRepository {


    List<RequirementSpecification> getCustomerRequirementsSpecificationsFileList(String costumerCode);

    RequirementSpecification getFileByName(String filename);
}
