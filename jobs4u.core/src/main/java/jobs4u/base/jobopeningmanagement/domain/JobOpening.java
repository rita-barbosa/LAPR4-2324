package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.interviewmodelmanagement.domain.InterviewModel;
import jobs4u.base.jobopeningmanagement.domain.rank.Rank;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.recruitmentprocessmanagement.domain.RecruitmentPeriod;
import jobs4u.base.recruitmentprocessmanagement.domain.RecruitmentProcess;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "T_JOBOPENING")
public class JobOpening implements AggregateRoot<JobReference>, DTOable<JobOpeningDTO> {

    private static final long serialVersionUID = 1L;
    @Version
    private Long version;

    @Column(nullable = false)
    private JobFunction function;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private ContractType contractType;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private  WorkMode workMode;
    @Column(nullable = false)
    private JobOpeningStatus status;
    @EmbeddedId
    private JobReference jobReference = new JobReference();
    @Column(nullable = false)
    private Address address;
    @Column(nullable = false)
    private Description description;
    @Column(nullable = false)
    private NumberVacancy numVacancies;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private RequirementSpecification requirementSpecification;

//    @ManyToOne(optional = false)
//    @JoinColumn(nullable = false)
//    private InterviewModel interviewModel;

//    @OneToMany
//    private final Set<Application> applications = new HashSet<>();

    @OneToOne
    private RecruitmentProcess recruitmentProcess;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Rank rank;

    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
                      String streetName, String city, String district, String streetNumber, String zipcode, Integer numVacancies,
                      String description, RequirementSpecification requirementsFile, JobReference lastReference) {

        Preconditions.noneNull(function, description, district, streetNumber, lastReference, requirementsFile, zipcode,
                city, contractTypeDenomination, workModeDenomination, numVacancies, streetName);

        JobReference newJobReference = generateNewSequencialJobReference(lastReference);
        this.jobReference = newJobReference;
        this.function = JobFunction.valueOf(function);
        this.address = new Address(streetName, city, district, streetNumber, zipcode);
        this.contractType = ContractType.valueOf(contractTypeDenomination.contractTypeName());
        this.workMode = WorkMode.valueOf(workModeDenomination.workModeName());
        this.description = Description.valueOf(description);
        this.numVacancies = NumberVacancy.valueOf(numVacancies);
        this.requirementSpecification = requirementsFile;
        this.status = new JobOpeningStatus();
        this.status.setStatusDescriptionAsUNFINISHED();
        this.rank = new Rank(newJobReference);
    }

    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
                      Address address, Integer numVacancies, String description, RequirementSpecification requirementsFile,
                      JobReference lastReference) {

        Preconditions.noneNull(function, description, address, lastReference, requirementsFile, contractTypeDenomination,
                workModeDenomination, numVacancies);

        JobReference newJobReference = generateNewSequencialJobReference(lastReference);
        this.jobReference = newJobReference;
        this.function = JobFunction.valueOf(function);
        this.address = address;
        this.contractType = ContractType.valueOf(contractTypeDenomination.contractTypeName());
        this.workMode = WorkMode.valueOf(workModeDenomination.workModeName());
        this.description = Description.valueOf(description);
        this.numVacancies = NumberVacancy.valueOf(numVacancies);
        this.requirementSpecification = requirementsFile;
        this.status = new JobOpeningStatus();
        this.status.setStatusDescriptionAsUNFINISHED();
        this.rank = new Rank(newJobReference);
    }

//    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
//                      String streetName, String city, String district, String streetNumber, String zipcode, Integer numVacancies,
//                      String description, RequirementSpecification requirementsFile, InterviewModel interviewFile, JobReference lastReference) {
//
//        Preconditions.noneNull(function, description, district, streetNumber, lastReference, requirementsFile, zipcode,
//                city, contractTypeDenomination, workModeDenomination, numVacancies, streetName);
//
//        JobReference newJobReference = generateNewSequencialJobReference(lastReference);
//        this.jobReference = newJobReference;
//        this.function = JobFunction.valueOf(function);
//        this.address = new Address(streetName, city, district, streetNumber, zipcode);
//        this.contractType = ContractType.valueOf(contractTypeDenomination.contractTypeName());
//        this.workMode = WorkMode.valueOf(workModeDenomination.workModeName());
//        this.description = Description.valueOf(description);
//        this.numVacancies = NumberVacancy.valueOf(numVacancies);
//        this.requirementSpecification = requirementsFile;
//        this.interviewModel = interviewFile;
//        this.status = new JobOpeningStatus();
//        this.status.setStatusDescriptionAsUNFINISHED();
//        this.rank = new Rank(newJobReference);
//    }
//
//    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
//                      Address address, Integer numVacancies, String description, RequirementSpecification requirementsFile,
//                      InterviewModel interviewFile, JobReference lastReference) {
//
//        Preconditions.noneNull(function, description, address, lastReference, requirementsFile, interviewFile, contractTypeDenomination,
//                workModeDenomination, numVacancies);
//
//        JobReference newJobReference = generateNewSequencialJobReference(lastReference);
//        this.jobReference = generateNewSequencialJobReference(lastReference);
//        this.function = JobFunction.valueOf(function);
//        this.address = address;
//        this.contractType = ContractType.valueOf(contractTypeDenomination.contractTypeName());
//        this.workMode = WorkMode.valueOf(workModeDenomination.workModeName());
//        this.description = Description.valueOf(description);
//        this.numVacancies = NumberVacancy.valueOf(numVacancies);
//        this.requirementSpecification = requirementsFile;
//        this.interviewModel = interviewFile;
//        this.status = new JobOpeningStatus();
//        this.status.setStatusDescriptionAsUNFINISHED();
//        this.rank = new Rank(newJobReference);
//    }

    protected JobOpening() {
        //for ORM
    }

    private JobReference generateNewSequencialJobReference(JobReference lastReference) {
        return new JobReference(lastReference.getcustomerCode(), lastReference.getSequentialCode() + 1);
    }

    public JobReference jobReference(){
        return identity();
    }

    public JobOpeningStatus jobOpeningStatus() {
        return this.status;
    }

    public void updateStatusToStarted() {
        this.status = new JobOpeningStatus(JobOpeningStatusEnum.STARTED);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public JobReference identity() {
        return this.jobReference;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobOpening)) return false;
        JobOpening that = (JobOpening) o;
        return Objects.equals(version, that.version) && Objects.equals(function, that.function) && Objects.equals(contractType, that.contractType) && Objects.equals(workMode, that.workMode) && status == that.status && Objects.equals(jobReference, that.jobReference) && Objects.equals(address, that.address) && Objects.equals(description, that.description) && Objects.equals(numVacancies, that.numVacancies) && Objects.equals(requirementSpecification, that.requirementSpecification) && Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    public JobFunction getFunction() {
        return function;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public WorkMode getWorkMode() {
        return workMode;
    }

    public JobOpeningStatus getStatus() {
        return status;
    }

    public JobReference getJobReference() {
        return jobReference;
    }

    public Address getAddress() {
        return address;
    }

    public Description getDescription() {
        return description;
    }

    public NumberVacancy getNumVacancies() {
        return numVacancies;
    }

    public RequirementSpecification getRequirementSpecification() {
        return requirementSpecification;
    }

    public Rank getRank() {
        return rank;
    }

//    public Set<Application> getApplications() {
//        return applications;
//    }

    @Override
    public JobOpeningDTO toDTO() {
        return new JobOpeningDTO(address.toString(), function.jobFunction(), description.description(), status.getStatusDescription(),
                contractType.getDenomination(), workMode.denomination(), String.valueOf(numVacancies.getNumVacancies()),
                requirementSpecification.requirementName().name(), jobReference.toString(), jobReference.getcustomerCode());
    }

    public void changeRequirementSpecification(RequirementSpecification requirementSpecification) {
        Preconditions.noneNull(requirementSpecification);
        Preconditions.ensure(status.getStatusDescription().equals(String.valueOf(JobOpeningStatusEnum.UNFINISHED)) ||
                status.getStatusDescription().equals(String.valueOf(JobOpeningStatusEnum.NOT_STARTED)));

        this.requirementSpecification = requirementSpecification;
    }

    public RecruitmentProcess getRecruitmentProcess() {
        return recruitmentProcess;
    }
}
