package jobs4u.base.interviewmodelmanagement.application;

import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.interviewmodelmanagement.domain.InterviewModel;
import jobs4u.base.interviewmodelmanagement.repositories.InterviewModelRepository;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;


import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class InterviewTemplateManagerService {

    private final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModels();

    public InterviewModel getInterviewFromJobOpening(JobOpeningDTO jobOpeningDTO) {
        Optional<InterviewModel> optional = interviewModelRepository.getFileByName(jobOpeningDTO.getInterviewModelName());
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NoSuchElementException("There are no interview models with the name provided.");
    }

    public boolean generateNewTemplate(InterviewModel interviewModel, String outputDirectory) {
        try {
            String[] command = {"java", "-jar", interviewModel.className(), "-template", (outputDirectory + "\\")};

            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            System.out.println("Template command executed. Process exited with code: " + exitCode);
            if (exitCode == 0){
                return true;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
