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
import jobs4u.base.recruitmentprocessmanagement.repository.RecruitmentProcessRepository;
import jobs4u.base.usermanagement.domain.BaseRoles;
import jobs4u.base.workmodemanagement.domain.WorkMode;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DomainEntitiesBootstrapper extends UsersBootstrapperBase implements Action {

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
        Calendar start1 = null;
        Calendar end1 = null;
        Calendar start2 = null;
        Calendar end2 = null;
        Calendar start3 = null;
        Calendar end3 = null;
        Calendar start4 = null;
        Calendar end4 = null;
        Calendar start5 = null;
        Calendar end5 = null;
        Calendar start6 = null;
        Calendar end6 = null;
        Calendar start7 = null;
        Calendar end7 = null;
        Calendar start8 = null;
        Calendar end8 = null;
        Calendar start9 = null;
        Calendar end9 = null;
        Calendar start10 = null;
        Calendar end10 = null;
        Calendar start11 = null;
        Calendar end11 = null;
        Calendar start12 = null;
        Calendar end12 = null;
        Calendar start13 = null;
        Calendar end13 = null;
        Calendar start14 = null;
        Calendar end14 = null;
        Calendar start15 = null;
        Calendar end15 = null;

        try {
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

            start1 = Calendars.fromDate(df.parse("25-04-2024"));
            end1 = Calendars.fromDate(df.parse("29-04-2024"));
            start2 = Calendars.fromDate(df.parse("01-05-2024"));
            end2 = Calendars.fromDate(df.parse("07-05-2024"));
            start3 = Calendars.fromDate(df.parse("08-05-2024"));
            end3 = Calendars.fromDate(df.parse("10-05-2024"));
            start4 = Calendars.fromDate(df.parse("11-05-2024"));
            end4 = Calendars.fromDate(df.parse("17-05-2024"));
            start5 = Calendars.fromDate(df.parse("25-05-2024"));
            end5 = Calendars.fromDate(df.parse("17-06-2024"));
            dateInterval1 = new DateInterval(start1, end5);

            start6 = Calendars.fromDate(df.parse("10-01-2024"));
            end6 = Calendars.fromDate(df.parse("13-01-2024"));
            start7 = Calendars.fromDate(df.parse("14-01-2024"));
            end7 = Calendars.fromDate(df.parse("17-01-2024"));
            start8 = Calendars.fromDate(df.parse("20-01-2024"));
            end8 = Calendars.fromDate(df.parse("21-01-2024"));
            start9 = Calendars.fromDate(df.parse("22-01-2024"));
            end9 = Calendars.fromDate(df.parse("24-01-2024"));
            start10 = Calendars.fromDate(df.parse("25-01-2024"));
            end10 = Calendars.fromDate(df.parse("30-01-2024"));
            dateInterval2 = new DateInterval(start6, end10);

            start11 = Calendars.fromDate(df.parse("10-02-2024"));
            end11 = Calendars.fromDate(df.parse("13-02-2024"));
            start12 = Calendars.fromDate(df.parse("14-02-2024"));
            end12 = Calendars.fromDate(df.parse("17-02-2024"));
            start13 = Calendars.fromDate(df.parse("20-02-2024"));
            end13 = Calendars.fromDate(df.parse("21-02-2024"));
            start14 = Calendars.fromDate(df.parse("22-02-2024"));
            end14 = Calendars.fromDate(df.parse("24-02-2024"));
            start15 = Calendars.fromDate(df.parse("25-02-2024"));
            end15 = Calendars.fromDate(df.parse("30-02-2024"));
            dateInterval3 = new DateInterval(start11, end15);
        }catch (ParseException e){
            System.out.println(e.getMessage());
        }

        List<Phase> phases1 = new ArrayList<>();

        List<Phase> phases2 = new ArrayList<>();

        List<Phase> phases3 = new ArrayList<>();

        Phase application1 = new Phase(String.valueOf(PhaseTypeEnum.APPLICATION), "Candidates send applications.", start1, end1);
        Phase screening1 = new Phase(String.valueOf(PhaseTypeEnum.SCREENING), "Candidates screening.",  start2, end2);
        Phase analysis1 = new Phase(String.valueOf(PhaseTypeEnum.ANALYSIS), "Candidates analysis.",  start3, end3);
        Phase interview1 = new Phase(String.valueOf(PhaseTypeEnum.INTERVIEW),"Candidates get interviewed.",  start4, end4);
        Phase result1 = new Phase(String.valueOf(PhaseTypeEnum.RESULTS),"Candidates get results.",  start5, end5);

        Phase application2 = new Phase(String.valueOf(PhaseTypeEnum.APPLICATION), "Candidates send applications.", start6, end6);
        Phase screening2 = new Phase(String.valueOf(PhaseTypeEnum.SCREENING),"Candidates screening.", start7, end7);
        Phase analysis2 = new Phase(String.valueOf(PhaseTypeEnum.ANALYSIS), "Candidates analysis.", start8, end8);
        Phase interview2 = new Phase(String.valueOf(PhaseTypeEnum.INTERVIEW), "Candidates get interviewed.", start9, end9);
        Phase result2 = new Phase(String.valueOf(PhaseTypeEnum.RESULTS),"Candidates get results.", start10, end10);


        Phase application3 = new Phase(String.valueOf(PhaseTypeEnum.APPLICATION),"Candidates send applications.", start11, end11);
        Phase screening3 = new Phase(String.valueOf(PhaseTypeEnum.SCREENING), "Candidates screening.", start12, end12);
        Phase analysis3 = new Phase(String.valueOf(PhaseTypeEnum.ANALYSIS), "Candidates analysis.", start13, end13);
        Phase interview3 = new Phase(String.valueOf(PhaseTypeEnum.INTERVIEW), "Candidates get interviewed.", start14, end14);
        Phase result3 = new Phase(String.valueOf(PhaseTypeEnum.RESULTS), "Candidates get results.", start15, end15);


        phases1.add(application1);
        phases1.add(screening1);
        phases1.add(interview1);
        phases1.add(analysis1);
        phases1.add(result1);

        phases2.add(application2);
        phases2.add(screening2);
        phases2.add(interview2);
        phases2.add(analysis2);
        phases2.add(result2);

        phases3.add(application3);
        phases3.add(screening3);
        phases3.add(interview3);
        phases3.add(analysis3);
        phases3.add(result3);

        RecruitmentProcess recruitmentProcess1 = new RecruitmentProcess(dateInterval1.start(), dateInterval1.end(), phases1, new RecruitmentProcessStatus(String.valueOf(RecruitmentProcessStatusEnum.PLANNED)));

        RecruitmentProcess recruitmentProcess2 = new RecruitmentProcess(dateInterval2.start(), dateInterval2.end(), phases2, new RecruitmentProcessStatus(String.valueOf(RecruitmentProcessStatusEnum.PLANNED)));

        RecruitmentProcess recruitmentProcess3 = new RecruitmentProcess(dateInterval3.start(), dateInterval3.end(), phases3, new RecruitmentProcessStatus(String.valueOf(RecruitmentProcessStatusEnum.PLANNED)));

        recruitmentProcess1 = recruitmentProcessRepository.save(recruitmentProcess1);
        recruitmentProcess2 = recruitmentProcessRepository.save(recruitmentProcess2);
        recruitmentProcess3 = recruitmentProcessRepository.save(recruitmentProcess3);

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
                "Flagtown", "Star District", "USA", "4500-900", 10, description,
                requirementSpecificationsList.get(0), interviewModelsList.get(0), jobReference);

        JobOpening jobOpening2 = new JobOpening("Back End Senior Developer", contract, mode, "456 Elm Street",
                "Maple Town", "Moonlight District", "Canada", "4500-900", 15, description,
                requirementSpecificationsList.get(0), interviewModelsList.get(0), jobReference1);

        JobOpening jobOpening3 = new JobOpening("Back End Senior Developer", contract, mode, "MM Street",
                "MM Town", "MM District", "MMM", "4500-900", 8, description,
                requirementSpecificationsList.get(0), interviewModelsList.get(0), jobReference2);

        JobOpening jobOpening4 = new JobOpening("Night Guard", contract, mode, "Freddy fazbear Street",
                "Chica Town", "Foxy District", "MMM", "4500-900", 8, description,
                requirementSpecificationsList.get(0), interviewModelsList.get(0), jobReference3);

        jobOpening1.updateStatusToNotStarted();
        jobOpening2.updateStatusToNotStarted();
        jobOpening3.updateStatusToNotStarted();

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
            recruitmentProcessList.get(i).referToJobOpening(jobOpenings.get(i));
            jobOpeningRepository.save(jobOpenings.get(i));
            recruitmentProcessRepository.save(recruitmentProcessList.get(i));
        }

    }

    private void persistRequirementSpecifications() {
        String description1 = "Back-End Developer With Experience in Java";
        String name1 = "Requirements_Back_End_Dev.jar";
        String fullClassName1 = "jobs4u.plugin.core.adapter.RequirementPluginAdapter";
        RequirementSpecification requirementSpecification1 = new RequirementSpecification(name1, description1, fullClassName1, "plugins-config-file/requirement/r-config-1.txt");

        String description2 = "Front-End Developer With Experience in Java";
        String name2 = "Requirements_Front_End_Dev.jar";
        String fullClassName2 = "jobs4u.plugin.core.adapter.RequirementPluginAdapter";
        RequirementSpecification requirementSpecification2 = new RequirementSpecification(name2, description2, fullClassName2, "plugins-config-file/requirement/r-config-1.txt");

        requirementSpecificationsList.add(requirementSpecification1);
        requirementSpecificationsList.add(requirementSpecification2);
        requirementSpecificationRepository.save(requirementSpecification1);
        requirementSpecificationRepository.save(requirementSpecification2);
    }

    private void persistInterviewModels() {
        String description1 = "Back-End Developer With Experience in Java";
        String name1 = "Interview_Back_End_Dev.jar";
        String fullClassName1 = "jobs4u.plugin.core.adapter.InterviewPluginAdapter";
        InterviewModel interviewModel1 = new InterviewModel(name1, description1, fullClassName1, "plugins-config-file/requirement/r-config-1.txt");


        String description2 = "Front-End Developer With Experience in Java";
        String name2 = "Interview_Front_End_Dev.jar";
        String fullClassName2 = "jobs4u.plugin.core.adapter.InterviewPluginAdapter";
        InterviewModel interviewModel2 = new InterviewModel(name2, description2, fullClassName2, "plugins-config-file/requirement/r-config-1.txt");

        interviewModelsList.add(interviewModel1);
        interviewModelsList.add(interviewModel2);

        interviewModelRepository.save(interviewModel1);
        interviewModelRepository.save(interviewModel2);
    }

    private void persistCustomers() {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CUSTOMER_MANAGER);
        SystemUser customerManager = registerUser("customerM2@email.com", "Password-1", "Joana", "Cash", roles);
        Address address = new Address("StreetLane", "City Garden", "District 9", "14th", "4590-890");

        customerManagementService.registerNewCustomer("Instituto Superior de Engenharia do Porto", address.toString(),
                "ISEP", customerManager, "isep@email.com");
    }

    private void persistCandidates() {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CANDIDATE_USER);
        PhoneNumber phoneNumber = new PhoneNumber("+351", "910000034");

        candidateManagementService.registerCandidate("Joana", "candidate@email.com", phoneNumber);
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

    private void persistApplications() {
        RequirementAnswer requirementAnswer = RequirementAnswer.valueOf("plugins-config-file/requirement/r-answer-1.txt");
        RequirementResult requirementResult =  RequirementResult.valueOf(true);
        ApplicationFile file = new ApplicationFile(new File("example.txt"));
        Set<ApplicationFile> files = new HashSet<>();
        files.add(file);
        Date date = new Date(2024 - 1900, Calendar.JANUARY, 5);
        Interview interview = new Interview("interview1", new Date(2024 - 1900, Calendar.MARCH, 2),
                new InterviewResult("passed", 50, "the grade is above 50"), "accepted");

        PhoneNumber phone = new PhoneNumber("+351", "910000034");

        Optional<Candidate> opCandidate = candidateRepository.findByPhoneNumber(phone);

        RequirementAnswer requirementAnswer1 = RequirementAnswer.valueOf("plugins-config-file/requirement/r-answer-1.txt");
        RequirementResult requirementResult1 =  RequirementResult.valueOf(true);
        ApplicationFile file1 = new ApplicationFile(new File("example1.txt"));
        Set<ApplicationFile> files1 = new HashSet<>();
        files1.add(file1);
        Date date1 = new Date(2024 - 1900, Calendar.JANUARY, 6);
        Interview interview1 = new Interview("interview1", new Date(2024 - 1900, Calendar.MARCH, 3),
                new InterviewResult("passed", 60, "the grade is above 50"), "accepted");

        Application application, application1;
        if (opCandidate.isPresent()) {
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