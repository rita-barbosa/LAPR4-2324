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
import jobs4u.base.contracttypemanagement.domain.ContractType;
import jobs4u.base.criteriamanagement.domain.Criteria;
import jobs4u.base.criteriamanagement.repository.CriteriaRepository;
import jobs4u.base.customermanagement.application.CustomerManagementService;
import jobs4u.base.infrastructure.bootstrapers.UsersBootstrapperBase;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.*;
import jobs4u.base.contracttypemanagement.dto.ContractTypeDTO;
import jobs4u.base.workmodemanagement.dto.WorkModeDTO;
import jobs4u.base.contracttypemanagement.repository.ContractTypeRepository;
import jobs4u.base.jobopeningmanagement.repositories.JobOpeningRepository;
import jobs4u.base.workmodemanagement.repository.WorkModeRepository;
import jobs4u.base.interviewmodelmanagement.domain.InterviewModel;
import jobs4u.base.interviewmodelmanagement.repositories.InterviewModelRepository;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.requirementsmanagement.repositories.RequirementSpecificationRepository;
import jobs4u.base.recruitmentprocessmanagement.domain.*;
import jobs4u.base.recruitmentprocessmanagement.repository.PhaseRepository;
import jobs4u.base.recruitmentprocessmanagement.repository.RecruitmentProcessRepository;
import jobs4u.base.usermanagement.domain.BaseRoles;
import jobs4u.base.workmodemanagement.domain.WorkMode;

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

    List<RecruitmentProcess> recruitmentProcessList = new ArrayList<>();

    @Override
    public boolean execute() {
        instantiateRepositories();
        persistCriteria();
        persistCustomers();
        persistCandidates();
        persistContractTypes();
        persistWorkModes();
        persistRequirementSpecifications();
        persistInterviewModels();
        persistRecruitmentProcesses();
        persistJobOpenings();
        persistApplications();
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
        DateInterval dateInterval1 = null;
        DateInterval dateInterval2 = null;
        DateInterval dateInterval3 = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

            Calendar start1 = Calendars.fromDate(df.parse("25-04-2024"));
            Calendar end1 = Calendars.fromDate(df.parse("17-06-2024"));
            dateInterval1 = new DateInterval(start1, end1);

            Calendar start2 = Calendars.fromDate(df.parse("10-01-2024"));
            Calendar end2 = Calendars.fromDate(df.parse("10-02-2024"));
            dateInterval2 = new DateInterval(start2, end2);

            Calendar start3 = Calendars.fromDate(df.parse("10-03-2024"));
            Calendar end3 = Calendars.fromDate(df.parse("10-04-2024"));
            dateInterval3 = new DateInterval(start3, end3);
        }catch (ParseException e){
            System.out.println(e.getMessage());
        }

        List<Phase> phases1 = new ArrayList<>();

        List<Phase> phases2 = new ArrayList<>();

        List<Phase> phases3 = new ArrayList<>();

        RecruitmentProcess recruitmentProcess1 = new RecruitmentProcess(dateInterval1.start(), dateInterval1.end(), phases1);

        RecruitmentProcess recruitmentProcess2 = new RecruitmentProcess(dateInterval2.start(), dateInterval2.end(), phases2);

        RecruitmentProcess recruitmentProcess3 = new RecruitmentProcess(dateInterval3.start(), dateInterval3.end(), phases3);


        Phase application1 = new Phase(new PhaseType(PhaseTypeEnum.APPLICATION), new PhaseDescription("Candidates send applications."),
                new PhaseStatus(PhaseStatusEnum.CONCLUDED), new PhasePeriod(dateInterval1));
        Phase screening1 = new Phase(new PhaseType(PhaseTypeEnum.SCREENING), new PhaseDescription("Candidates screening."),
                new PhaseStatus(PhaseStatusEnum.ON_GOING), new PhasePeriod(dateInterval1));
        Phase analysis1 = new Phase(new PhaseType(PhaseTypeEnum.ANALYSIS), new PhaseDescription("Candidates analysis."),
                new PhaseStatus(PhaseStatusEnum.PLANNED), new PhasePeriod(dateInterval1));
        Phase interview1 = new Phase(new PhaseType(PhaseTypeEnum.INTERVIEWS), new PhaseDescription("Candidates get interviewed."),
                new PhaseStatus(PhaseStatusEnum.PLANNED), new PhasePeriod(dateInterval1));
        Phase result1 = new Phase(new PhaseType(PhaseTypeEnum.RESULT), new PhaseDescription("Candidates get results."),
                new PhaseStatus(PhaseStatusEnum.PLANNED), new PhasePeriod(dateInterval1));

        Phase application2 = new Phase(new PhaseType(PhaseTypeEnum.APPLICATION), new PhaseDescription("Candidates send applications."),
                new PhaseStatus(PhaseStatusEnum.CONCLUDED), new PhasePeriod(dateInterval2));
        Phase screening2 = new Phase(new PhaseType(PhaseTypeEnum.SCREENING), new PhaseDescription("Candidates screening."),
                new PhaseStatus(PhaseStatusEnum.ON_GOING), new PhasePeriod(dateInterval2));
        Phase analysis2 = new Phase(new PhaseType(PhaseTypeEnum.ANALYSIS), new PhaseDescription("Candidates analysis."),
                new PhaseStatus(PhaseStatusEnum.PLANNED), new PhasePeriod(dateInterval2));
        Phase interview2 = new Phase(new PhaseType(PhaseTypeEnum.INTERVIEWS), new PhaseDescription("Candidates get interviewed."),
                new PhaseStatus(PhaseStatusEnum.PLANNED), new PhasePeriod(dateInterval2));
        Phase result2 = new Phase(new PhaseType(PhaseTypeEnum.RESULT), new PhaseDescription("Candidates get results."),
                new PhaseStatus(PhaseStatusEnum.PLANNED), new PhasePeriod(dateInterval2));


        Phase application3 = new Phase(new PhaseType(PhaseTypeEnum.APPLICATION), new PhaseDescription("Candidates send applications."),
                new PhaseStatus(PhaseStatusEnum.CONCLUDED), new PhasePeriod(dateInterval3));
        Phase screening3 = new Phase(new PhaseType(PhaseTypeEnum.SCREENING), new PhaseDescription("Candidates screening."),
                new PhaseStatus(PhaseStatusEnum.ON_GOING), new PhasePeriod(dateInterval3));
        Phase analysis3 = new Phase(new PhaseType(PhaseTypeEnum.ANALYSIS), new PhaseDescription("Candidates analysis."),
                new PhaseStatus(PhaseStatusEnum.PLANNED), new PhasePeriod(dateInterval3));
        Phase interview3 = new Phase(new PhaseType(PhaseTypeEnum.INTERVIEWS), new PhaseDescription("Candidates get interviewed."),
                new PhaseStatus(PhaseStatusEnum.PLANNED), new PhasePeriod(dateInterval3));
        Phase result3 = new Phase(new PhaseType(PhaseTypeEnum.RESULT), new PhaseDescription("Candidates get results."),
                new PhaseStatus(PhaseStatusEnum.PLANNED), new PhasePeriod(dateInterval3));


        phases1.add(application1);
        phases1.add(screening1);
        phases1.add(analysis1);
        phases1.add(interview1);
        phases1.add(result1);

        phases2.add(application2);
        phases2.add(screening2);
        phases2.add(analysis2);
        phases2.add(interview2);
        phases2.add(result2);

        phases3.add(application3);
        phases3.add(screening3);
        phases3.add(analysis3);
        phases3.add(interview3);
        phases3.add(result3);

        List<Phase> phases11 = new ArrayList<>();
        List<Phase> phases22 = new ArrayList<>();
        List<Phase> phases33 = new ArrayList<>();
        for (Phase phase : phases1){
            Phase phase1 = phaseRepository.save(phase);
            phases11.add(phase1);
        }
        for (Phase phase : phases2){
            Phase phase2 = phaseRepository.save(phase);
            phases22.add(phase2);
        }
        for (Phase phase : phases3){
            Phase phase3 = phaseRepository.save(phase);
            phases33.add(phase3);
        }

        recruitmentProcess1.setPhases(phases11);
        recruitmentProcess2.setPhases(phases22);
        recruitmentProcess3.setPhases(phases33);
        recruitmentProcess1 = recruitmentProcessRepository.save(recruitmentProcess1);
        recruitmentProcess2 = recruitmentProcessRepository.save(recruitmentProcess2);
        recruitmentProcess3 = recruitmentProcessRepository.save(recruitmentProcess3);

        for (Phase phase : phases1){
            phase.setRecruitmentprocess(recruitmentProcess1);
            phaseRepository.save(phase);
        }
        for (Phase phase : phases2){
            phase.setRecruitmentprocess(recruitmentProcess2);
            phaseRepository.save(phase);
        }
        for (Phase phase : phases3){
            phase.setRecruitmentprocess(recruitmentProcess3);
            phaseRepository.save(phase);
        }

        recruitmentProcessList.add(recruitmentProcess1);
        recruitmentProcessList.add(recruitmentProcess2);
        recruitmentProcessList.add(recruitmentProcess3);
    }



    private void persistJobOpenings() {
        ContractTypeDTO contract = new ContractTypeDTO("full-time");
        WorkModeDTO mode = new WorkModeDTO("remote");
        String description = "Night Guard.";

        JobReference jobReference = new JobReference("ISEP", 0);
        JobReference jobReference1 = new JobReference("ISEP", 1);
        JobReference jobReference2 = new JobReference("ISEP", 2);
        JobReference jobReference3 = new JobReference("ISEP", 4);

        JobOpening jobOpening1 = new JobOpening("Front End Junior Developer", contract, mode, "123 Main Street",
                "Flagtown", "Star District", "USA", "12345", 10, description,
                requirementSpecificationsList.get(0),interviewModelsList.get(0), jobReference);

        JobOpening jobOpening2 = new JobOpening("Back End Senior Developer", contract, mode, "456 Elm Street",
                "Maple Town", "Moonlight District", "Canada", "54321", 15, description,
                requirementSpecificationsList.get(0),interviewModelsList.get(0), jobReference1);

        JobOpening jobOpening3 = new JobOpening("Back End Senior Developer", contract, mode, "MM Street",
                "MM Town", "MM District", "MMM", "54321", 8, description,
                requirementSpecificationsList.get(0), interviewModelsList.get(0),jobReference2);

        JobOpening jobOpening4 = new JobOpening("Night Guard", contract, mode, "Freddy fazbear Street",
                "Chica Town", "Foxy District", "MMM", "54321", 8, description,
                requirementSpecificationsList.get(0), interviewModelsList.get(0),jobReference3);

        jobOpening2.getStatus().setStatusDescriptionAsSTARTED();

        jobOpeningRepository.save(jobOpening4);
        jobOpening1 = jobOpeningRepository.save(jobOpening1);
        jobOpening2 = jobOpeningRepository.save(jobOpening2);
        jobOpening3 = jobOpeningRepository.save(jobOpening3);

        List<JobOpening> jobOpenings = new ArrayList<>();

        jobOpenings.add(jobOpening1);
        jobOpenings.add(jobOpening2);
        jobOpenings.add(jobOpening3);

        for (int i = 0; i < 3; i++) {
            jobOpenings.get(i).addRecruitmentProcess(recruitmentProcessList.get(i));
            jobOpenings.get(i).updateStatusToNotStarted();
            recruitmentProcessList.get(i).setJobOpening(jobOpenings.get(i));
            jobOpeningRepository.save(jobOpenings.get(i));
            recruitmentProcessRepository.save(recruitmentProcessList.get(i));
        }

    }

    private void persistRequirementSpecifications() {
        String description1 = "Back-End Developer With Experience in Java";
        String name1 = "Requirements_Back_End_Dev.jar";
        String fullClassName1 = "Plugins/Requirements/Back_End_Dev/Requirements_Back_End_Dev.jar";
        RequirementSpecification requirementSpecification1 = new RequirementSpecification(name1, description1, fullClassName1);

        String description2 = "Front-End Developer With Experience in Java";
        String name2 = "Requirements_Front_End_Dev.jar";
        String fullClassName2 = "Plugins/Requirements/Front_End_Dev/Requirements_Front_End_Dev.jar";
        RequirementSpecification requirementSpecification2 = new RequirementSpecification(name2, description2, fullClassName2);

        requirementSpecificationsList.add(requirementSpecification1);
        requirementSpecificationsList.add(requirementSpecification2);
        requirementSpecificationRepository.save(requirementSpecification1);
        requirementSpecificationRepository.save(requirementSpecification2);
    }

    private void persistInterviewModels() {
        String description1 = "Back-End Developer With Experience in Java";
        String name1 = "Interview_Back_End_Dev.jar";
        String fullClassName1 = "Plugins/Interviews/Back_End_Dev/Interview_Back_End_Dev.jar";
        InterviewModel interviewModel1 = new InterviewModel(name1, description1, fullClassName1);


        String description2 = "Front-End Developer With Experience in Java";
        String name2 = "Interview_Front_End_Dev.jar";
        String fullClassName2 = "Plugins/Interviews/Front_End_Dev/Interview_Front_End_Dev.jar";
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