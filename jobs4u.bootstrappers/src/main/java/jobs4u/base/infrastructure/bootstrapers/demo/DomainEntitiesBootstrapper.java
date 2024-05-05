package jobs4u.base.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.domain.model.DateInterval;
import eapli.framework.time.util.Calendars;
import jobs4u.base.applicationmanagement.domain.*;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.candidatemanagement.application.CandidateManagementService;
import jobs4u.base.candidatemanagement.domain.Candidate;
import jobs4u.base.candidatemanagement.domain.PhoneNumber;
import jobs4u.base.candidatemanagement.repository.CandidateRepository;
import jobs4u.base.criteriamanagement.domain.Criteria;
import jobs4u.base.criteriamanagement.repository.CriteriaRepository;
import jobs4u.base.customermanagement.application.CustomerManagementService;
import jobs4u.base.infrastructure.bootstrapers.UsersBootstrapperBase;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.*;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;
import jobs4u.base.jobopeningmanagement.repositories.ContractTypeRepository;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;
import jobs4u.base.jobopeningmanagement.repositories.WorkModeRepository;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.domain.InterviewModel;
import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.repositories.InterviewModelRepository;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.FullClassName;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementDescription;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementName;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.repositories.RequirementSpecificationRepository;
import jobs4u.base.recruitmentprocessmanagement.domain.*;
import jobs4u.base.recruitmentprocessmanagement.repository.PhaseRepository;
import jobs4u.base.recruitmentprocessmanagement.repository.RecruitmentProcessRepository;
import jobs4u.base.usermanagement.domain.BaseRoles;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DomainEntitiesBootstrapper  extends UsersBootstrapperBase implements Action {

    CustomerManagementService customerManagementService = new CustomerManagementService();

    CandidateManagementService candidateManagementService = new CandidateManagementService();

    private ContractTypeRepository contractTypeRepository;
    private WorkModeRepository workModeRepository;
    private RequirementSpecificationRepository requirementSpecificationRepository;
    private InterviewModelRepository interviewModelRepository;
    private JobOpeningRepository jobOpeningRepository;
    List<InterviewModel> interviewModelsList = new ArrayList<>();
    private CriteriaRepository criteriaRepository;
    private RecruitmentProcessRepository recruitmentProcessRepository;
    private PhaseRepository phaseRepository;

    private ApplicationRepository applicationRepository;

    private CandidateRepository candidateRepository;

    List<RequirementSpecification> requirementSpecificationsList = new ArrayList<>();

    @Override
    public boolean execute() {
        instantiateRepositories();
        persistCriteria();
        persistCustomers();
        persistContractTypes();
        persistWorkModes();
        persistRequirementSpecifications();
        persistInterviewModels();
        persistJobOpenings();
        persistCandidates();
        persistApplications();
        //persistRecruitmentProcesses();
        return true;
    }

    public void instantiateRepositories() {
        this.contractTypeRepository = PersistenceContext.repositories().contractTypes();
        this.workModeRepository = PersistenceContext.repositories().workModes();
        this.requirementSpecificationRepository = PersistenceContext.repositories().requirementSpecifications();
        this.jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
        this.interviewModelRepository = PersistenceContext.repositories().interviewModels();
        this.criteriaRepository = PersistenceContext.repositories().criteria();
        this.recruitmentProcessRepository = PersistenceContext.repositories().recruitmentProcesses();
        this.phaseRepository = PersistenceContext.repositories().phases();
        this.candidateRepository = PersistenceContext.repositories().candidates();
        this.applicationRepository = PersistenceContext.repositories().applications();
    }

    private void persistCriteria() {
        criteriaRepository.save(new Criteria("Status [STARTED]", JobOpening.class.getSimpleName()));
        criteriaRepository.save(new Criteria("Company Name", JobOpening.class.getSimpleName()));
        criteriaRepository.save(new Criteria("Customer Code", JobOpening.class.getSimpleName()));
        criteriaRepository.save(new Criteria("Time Interval", JobOpening.class.getSimpleName()));
    }

    private void persistRecruitmentProcesses() {
        ContractTypeDTO contract = new ContractTypeDTO("full-time");
        WorkModeDTO mode = new WorkModeDTO("remote");
        String description = "UX/UI Design.";
        JobReference jobReference2 = new JobReference("ISEP", 3);

        JobOpening jobOpening2 = new JobOpening("Back End Senior Developer", contract, mode, "HELLO Street",
                "HELLO Town", "HELLO District", "HELLO", "85206", 8, description,
                requirementSpecificationsList.get(0), jobReference2);
        jobOpening2.getStatus().setStatusDescriptionAsSTARTED();

        DateInterval dateInterval = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Calendar start = Calendars.fromDate(df.parse("25-04-2024"));
            Calendar end = Calendars.fromDate(df.parse("17-06-2024"));
            dateInterval = new DateInterval(start, end);
        }catch (ParseException e){
            System.out.println(e.getMessage());
        }

        List<Phase> phases = new ArrayList<>();
        RecruitmentProcess recruitmentProcess = new RecruitmentProcess(new RecruitmentPeriod(dateInterval), jobOpening2, phases);

        Phase application = new Phase(new PhaseType(PhaseTypeEnum.APPLICATION), new PhaseDescription("Candidates send applications."),
                new PhaseStatus(PhaseStatusEnum.CONCLUDED), new PhasePeriod()/*, recruitmentProcess*/);
        Phase screening = new Phase(new PhaseType(PhaseTypeEnum.SCREENING), new PhaseDescription("Candidates screening."),
                new PhaseStatus(PhaseStatusEnum.ON_GOING), new PhasePeriod()/*, recruitmentProcess*/);
        Phase analysis = new Phase(new PhaseType(PhaseTypeEnum.ANALYSIS), new PhaseDescription("Candidates analysis."),
                new PhaseStatus(PhaseStatusEnum.PLANNED), new PhasePeriod()/*, recruitmentProcess*/);
        Phase interview = new Phase(new PhaseType(PhaseTypeEnum.INTERVIEWS), new PhaseDescription("Candidates get interviewed."),
                new PhaseStatus(PhaseStatusEnum.PLANNED), new PhasePeriod()/*, recruitmentProcess*/);
        Phase result = new Phase(new PhaseType(PhaseTypeEnum.RESULT), new PhaseDescription("Candidates get results."),
                new PhaseStatus(PhaseStatusEnum.PLANNED), new PhasePeriod()/*, recruitmentProcess*/);

        phases.add(application);
        phases.add(screening);
        phases.add(analysis);
        phases.add(interview);
        phases.add(result);

        for (Phase phase : phases){
            phaseRepository.save(phase);
        }

        recruitmentProcess.setPhases(phases);
        recruitmentProcessRepository.save(recruitmentProcess);
    }



    private void persistJobOpenings() {
        ContractTypeDTO contract = new ContractTypeDTO("full-time");
        WorkModeDTO mode = new WorkModeDTO("remote");
        String description = "UX/UI Design.";

        JobReference jobReference = new JobReference("ISEP", 0);
        JobReference jobReference1 = new JobReference("ISEP", 1);
        JobReference jobReference2 = new JobReference("ISEP", 2);

        JobOpening jobOpening = new JobOpening("Front End Junior Developer", contract, mode, "123 Main Street",
                "Flagtown", "Star District", "USA", "12345", 10, description,
                requirementSpecificationsList.get(1),interviewModelsList.get(0), jobReference);

        JobOpening jobOpening1 = new JobOpening("Back End Senior Developer", contract, mode, "456 Elm Street",
                "Maple Town", "Moonlight District", "Canada", "54321", 15, description,
                requirementSpecificationsList.get(0),interviewModelsList.get(0), jobReference1);

        JobOpening jobOpening2 = new JobOpening("Back End Senior Developer", contract, mode, "MM Street",
                "MM Town", "MM District", "MMM", "54321", 8, description,
                requirementSpecificationsList.get(0), interviewModelsList.get(0),jobReference2);
        jobOpening2.getStatus().setStatusDescriptionAsSTARTED();

        jobOpeningRepository.save(jobOpening);
        jobOpeningRepository.save(jobOpening1);
        jobOpeningRepository.save(jobOpening2);
    }

    private void persistRequirementSpecifications() {
        String description1 = "Back-End Developer With Experience in Java";
        String name1 = "Back_End_Dev_Requirement_Plugin.jar";
        String fullClassName1 = "plugin.src.java.pluginRequirement";
        RequirementSpecification requirementSpecification1 = new RequirementSpecification(name1, description1, fullClassName1);


        String description2 = "Front-End Developer With Experience in HTML";
        String name2 = "Front_End_Dev_Requirement_Plugin.jar";
        String fullClassName2 = "plugin.src.java.pluginRequirement";
        RequirementSpecification requirementSpecification2 = new RequirementSpecification(name2, description2, fullClassName2);

        requirementSpecificationsList.add(requirementSpecification1);
        requirementSpecificationsList.add(requirementSpecification2);

        requirementSpecificationRepository.save(requirementSpecification1);
        requirementSpecificationRepository.save(requirementSpecification2);
    }

    private void persistInterviewModels() {
        String description1 = "Back-End Developer With Experience in Java";
        String name1 = "Back_End_Dev_Interview_Plugin.jar";
        String fullClassName1 = "plugin.src.java.pluginInterview";
        InterviewModel interviewModel1 = new InterviewModel(name1, description1, fullClassName1);


        String description2 = "Front-End Developer With Experience in Java";
        String name2 = "Front_End_Dev_Interview_Plugin.jar";
        String fullClassName2 = "plugin.src.java.pluginInterview";
        InterviewModel interviewModel2 = new InterviewModel(name2, description2, fullClassName2);

        interviewModelsList.add(interviewModel1);
        interviewModelsList.add(interviewModel2);

        interviewModelRepository.save(interviewModel1);
        interviewModelRepository.save(interviewModel2);
    }

    private void persistCustomers() {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CUSTOMER_MANAGER);
        SystemUser customerManager = registerUser("customerM2@email.com", "Password-1", "Joana", "Cash", roles);
        Address address = new Address("StreetLane", "City Garden", "District 9", "14th", "15632");

        customerManagementService.registerNewCustomer("Instituto Superior de Engenharia do Porto", address.toString(),
                "ISEP", customerManager, "isep@email.com");
    }

    private void persistCandidates(){
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CANDIDATE_USER);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "910000034");

        candidateManagementService.registerCandidate("Joana","candidate@email.com",phoneNumber);
    }

    private void persistWorkModes() {
        WorkMode workMode = WorkMode.valueOf("remote");
        WorkMode workMode1 = WorkMode.valueOf("hybrid");
        WorkMode workMode2 = WorkMode.valueOf("onsite");

        workModeRepository.save(workMode);
        workModeRepository.save(workMode1);
        workModeRepository.save(workMode2);
    }

    private void persistContractTypes() {
        ContractType contractType = ContractType.valueOf("full-time");
        ContractType contractType1 = ContractType.valueOf("part-time");

        contractTypeRepository.save(contractType);
        contractTypeRepository.save(contractType1);
    }

    private void persistApplications(){
        RequirementAnswer requirementAnswer = new RequirementAnswer("The requirement was complete!");
        RequirementResult requirementResult = new RequirementResult(true);
        ApplicationFile file = new ApplicationFile(new File("example.txt"));
        Set<ApplicationFile> files = new HashSet<>();
        files.add(file);
        Date date = new Date(2024 - 1900, Calendar.JANUARY, 5);
        Interview interview = new Interview("interview1", new Date(2024 - 1900, Calendar.MARCH, 2),
                new InterviewResult("passed", 50, "the grade is above 50"), "accepted");

        PhoneNumber phone = new PhoneNumber("+351", "910000034");

        Optional<Candidate> opCandidate = candidateRepository.findByPhoneNumber(phone);

        RequirementAnswer requirementAnswer1 = new RequirementAnswer("The requirement was complete!");
        RequirementResult requirementResult1 = new RequirementResult(true);
        ApplicationFile file1 = new ApplicationFile(new File("example1.txt"));
        Set<ApplicationFile> files1 = new HashSet<>();
        files1.add(file1);
        Date date1 = new Date(2024 - 1900, Calendar.JANUARY, 6);
        Interview interview1 = new Interview("interview1", new Date(2024 - 1900, Calendar.MARCH, 3),
                new InterviewResult("passed", 60, "the grade is above 50"), "accepted");

        Application application, application1;
        if (opCandidate.isPresent()){
            Candidate candidate = opCandidate.get();
            application = new Application(requirementAnswer, requirementResult, files, date, candidate, interview);
            application1 = new Application(requirementAnswer1, requirementResult1, files1, date1, candidate, interview1);
        } else {
            application = new Application(requirementAnswer, requirementResult, files, date, interview);
            application1 = new Application(requirementAnswer1, requirementResult1, files1, date1, interview1);
        }


        Set<Application> applicationsSet = new HashSet<>();
        applicationsSet.add(application);

        Set<Application> applicationsSet1 = new HashSet<>();
        applicationsSet1.add(application1);

        List<JobOpening> jobs = new ArrayList<>();
        for (JobOpening job : jobOpeningRepository.findAll()) {
            jobs.add(job);
        }

        jobs.get(0).setApplications(applicationsSet);
        jobOpeningRepository.save(jobs.get(0));

        jobs.get(1).setApplications(applicationsSet1);
        jobOpeningRepository.save(jobs.get(1));
    }
}
