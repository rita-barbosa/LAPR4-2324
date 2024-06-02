package jobs4u.base.requirementsmanagement.application;

import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.requirementsmanagement.repositories.RequirementSpecificationRepository;


import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class RequirementsTemplateManagerService {

    private final RequirementSpecificationRepository requirementSpecificationRepository = PersistenceContext
            .repositories().requirementSpecifications();

    public RequirementSpecification getRequirementFromJobOpeningDTO(JobOpeningDTO jobOpeningDTO) {
        Optional<RequirementSpecification> optional = requirementSpecificationRepository.requirementSpecificationByRequirementName(jobOpeningDTO.getRequirementName());
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NoSuchElementException("There are no requirement specifications with the name provided.");
    }

    public boolean generateNewTemplate(RequirementSpecification requirementSpecification, String outputDirectory) {
        try {
            String[] command = {"java", "-jar", requirementSpecification.className().toString(), "-template", (outputDirectory + "\\")};

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            System.out.println("Template command executed. Process exited with code: " + exitCode);
            if (exitCode == 0) {
                return true;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

}

