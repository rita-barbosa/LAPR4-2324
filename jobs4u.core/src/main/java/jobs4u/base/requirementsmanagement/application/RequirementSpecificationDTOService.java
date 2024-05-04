package jobs4u.base.requirementsmanagement.application;

import eapli.framework.validations.Preconditions;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.dto.RequirementSpecificationDTO;

import java.util.ArrayList;
import java.util.List;

public class RequirementSpecificationDTOService {
    public Iterable<RequirementSpecificationDTO> convertToDTO(Iterable<RequirementSpecification> requirementSpecifications) {
        Preconditions.noneNull(requirementSpecifications);

        List<RequirementSpecificationDTO> dtos = new ArrayList<>();
        for (RequirementSpecification r : requirementSpecifications) {
            dtos.add(r.toDTO());
        }
        return dtos;
    }
}