package jobs4u.base.infrastructure.bootstrapers.demo;

import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.domain.model.DateInterval;
import eapli.framework.time.util.Calendars;
import jobs4u.base.applicationmanagement.domain.*;
import jobs4u.base.applicationmanagement.repositories.ApplicationRepository;
import jobs4u.base.candidatemanagement.application.CandidateManagementService;
import jobs4u.base.candidatemanagement.domain.PhoneNumber;
import jobs4u.base.criteriamanagement.domain.Criteria;
import jobs4u.base.criteriamanagement.repository.CriteriaRepository;
import jobs4u.base.customermanagement.application.CustomerManagementService;
import jobs4u.base.infrastructure.bootstrapers.UsersBootstrapperBase;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.interviewmodelmanagement.domain.InterviewModel;
import jobs4u.base.interviewmodelmanagement.domain.InterviewModelDescription;
import jobs4u.base.interviewmodelmanagement.domain.InterviewModelName;
import jobs4u.base.interviewmodelmanagement.repositories.InterviewModelRepository;
import jobs4u.base.jobopeningmanagement.domain.*;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;
import jobs4u.base.jobopeningmanagement.repositories.ContractTypeRepository;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;
import jobs4u.base.jobopeningmanagement.repositories.WorkModeRepository;
import jobs4u.base.recruitmentprocessmanagement.domain.*;
import jobs4u.base.recruitmentprocessmanagement.repository.PhaseRepository;
import jobs4u.base.recruitmentprocessmanagement.repository.RecruitmentProcessRepository;
import jobs4u.base.requirementsmanagement.domain.PluginJarFile;
import jobs4u.base.requirementsmanagement.domain.RequirementDescription;
import jobs4u.base.requirementsmanagement.domain.RequirementName;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.requirementsmanagement.repositories.RequirementSpecificationRepository;
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
    private CriteriaRepository criteriaRepository;
    private RecruitmentProcessRepository recruitmentProcessRepository;
    private PhaseRepository phaseRepository;

    private ApplicationRepository applicationRepository;

    List<RequirementSpecification> requirementSpecificationsList = new ArrayList<>();
    List<InterviewModel> interviewModelList = new ArrayList<>();

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
        this.interviewModelRepository = PersistenceContext.repositories().interviewModels();
        this.jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
        this.criteriaRepository = PersistenceContext.repositories().criteria();
        this.recruitmentProcessRepository = PersistenceContext.repositories().recruitmentProcesses();
        this.phaseRepository = PersistenceContext.repositories().phases();
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
                requirementSpecificationsList.get(1), jobReference);

        JobOpening jobOpening1 = new JobOpening("Back End Senior Developer", contract, mode, "456 Elm Street",
                "Maple Town", "Moonlight District", "Canada", "54321", 15, description,
                requirementSpecificationsList.get(0), jobReference1);

        JobOpening jobOpening2 = new JobOpening("Back End Senior Developer", contract, mode, "MM Street",
                "MM Town", "MM District", "MMM", "54321", 8, description,
                requirementSpecificationsList.get(0), jobReference2);
        jobOpening2.getStatus().setStatusDescriptionAsSTARTED();

        jobOpeningRepository.save(jobOpening);
        jobOpeningRepository.save(jobOpening1);
        jobOpeningRepository.save(jobOpening2);
    }

    private void persistRequirementSpecifications() {
        RequirementName requirementName = new RequirementName("Back End Senior Developer");
        RequirementDescription requirementDescription = new RequirementDescription("Requirements for back end senior developers");
        PluginJarFile pluginJarFile = new PluginJarFile("back-end-senior-developer.jar");
        RequirementSpecification requirementSpecification = new RequirementSpecification(requirementName, requirementDescription,
                pluginJarFile);

        RequirementName requirementName1 = new RequirementName("Front End Junior Developer");
        RequirementDescription requirementDescription1 = new RequirementDescription("Requirements for front end junior developers");
        PluginJarFile pluginJarFile1 = new PluginJarFile("front-end-junior-developer.jar");
        RequirementSpecification requirementSpecification1 = new RequirementSpecification(requirementName1, requirementDescription1,
                pluginJarFile1);

        requirementSpecificationsList.add(requirementSpecification);
        requirementSpecificationsList.add(requirementSpecification1);

        requirementSpecificationRepository.save(requirementSpecification);
        requirementSpecificationRepository.save(requirementSpecification1);
    }

    private void persistInterviewModels(){
        InterviewModelName interviewName = new InterviewModelName("[Back End] Senior Developer");
        InterviewModelDescription interviewModelDescription = new InterviewModelDescription("Interview for back end senior developers");
        PluginJarFile pluginJarFile = new PluginJarFile("interview-back-end-senior-developer.jar");
        InterviewModel interviewModel = new InterviewModel(interviewName, interviewModelDescription, pluginJarFile);

        InterviewModelName interviewName1 = new InterviewModelName("[Front End] Junior Developer");
        InterviewModelDescription interviewModelDescription1 = new InterviewModelDescription("Interview for front end junior developers");
        PluginJarFile pluginJarFile1 = new PluginJarFile("interview-front-end-junior-developer.jar");
        InterviewModel interviewModel1 = new InterviewModel(interviewName1, interviewModelDescription1, pluginJarFile1);

        interviewModelList.add(interviewModel);
        interviewModelList.add(interviewModel1);

        interviewModelRepository.save(interviewModel);
        interviewModelRepository.save(interviewModel1);
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
        File file = new File("example.txt");
        Date date = new Date(2024, Calendar.JANUARY, 5);
        Interview interview = new Interview("interview1", new Date(2024, Calendar.FEBRUARY, 2),
                new InterviewResult("correct", 50, "wrong"), "answer");


        Application application = new Application(1L, requirementAnswer, requirementResult, file, date, interview);

        applicationRepository.save(application);
    }
}
