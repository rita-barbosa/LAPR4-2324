package jobs4u.base.app.backoffice.console.presentation.operator;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import jobs4u.base.applicationmanagement.application.RegisterJobOpeningApplicationController;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.applicationmanagement.domain.ApplicationFile;
import jobs4u.base.candidatemanagement.domain.PhoneNumber;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RegisterJobOpeningApplicationUI extends AbstractUI {

    private static final RegisterJobOpeningApplicationController controller = new RegisterJobOpeningApplicationController();
    @Override
    protected boolean doShow() {
        final String sharedFolderPath = Console.readNonEmptyLine("Shared Folder", "Please enter the shared folder");

        File folder = new File(sharedFolderPath);

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith("-candidate-data.txt")) {
                        try (Scanner scanner = new Scanner(file)) {
                            String jobReference = scanner.nextLine();
                            String email = scanner.nextLine();
                            String name = scanner.nextLine();
                            String phoneNumber = scanner.nextLine();


                            boolean validate = controller.registerCandidate(name, email, "+351", phoneNumber);

                            Set<ApplicationFile> applicationFiles = new HashSet<>();

                            for (File otherFile : files) {
                                applicationFiles.add(new ApplicationFile(otherFile));
                            }

                            PhoneNumber number = new PhoneNumber("+351", phoneNumber);

                            try {
                                Optional<Application> application = controller.registerApplication(applicationFiles, new Date(), number);
                                if (application.isEmpty()) {
                                    throw new IntegrityViolationException();
                                } else {
                                    System.out.println("The application was successfully registered!\n");
                                }
                            } catch (final IntegrityViolationException | ConcurrencyException e) {
                                System.out.println("An error occurred while registering the job opening.\n" + e.getMessage());
                            }

                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        } else{
            System.out.println("Invalid directory: " + sharedFolderPath);
        }
        return true;
    }

    @Override
    public String headline() {
        return "Register a Job Application";
    }

}
