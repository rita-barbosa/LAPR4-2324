package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.base.applicationmanagement.domain.Application;
import jobs4u.base.contracttypemanagement.domain.ContractType;
import jobs4u.base.jobopeningmanagement.domain.rank.Rank;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.interviewmodelmanagement.domain.InterviewModel;
import jobs4u.base.recruitmentprocessmanagement.domain.RecruitmentProcess;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.contracttypemanagement.dto.ContractTypeDTO;
import jobs4u.base.workmodemanagement.dto.WorkModeDTO;
import jobs4u.base.workmodemanagement.domain.WorkMode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
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
    private WorkMode workMode;
    @Getter
    @Column(nullable = false)
    private JobOpeningStatus status;
    @Getter
    @EmbeddedId
    private JobReference jobReference = new JobReference();
    @Getter
    @Column(nullable = false)
    private Address address;
    @Getter
    @Column(nullable = false)
    private Description description;
    @Column(nullable = false)
    private NumberVacancy numVacancies;
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private RequirementSpecification requirementSpecification;

    //tem de ser true, porque quando se regista uma job opening esta ainda nao tem interview model
    @ManyToOne(optional = true)
    private InterviewModel interviewModel;


    @Setter
    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Application> applications = new HashSet<>();

    @Getter
    @OneToOne(mappedBy = "jobOpening")
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

    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
                      String streetName, String city, String district, String streetNumber, String zipcode, Integer numVacancies,
                      String description, RequirementSpecification requirementsFile, InterviewModel interviewFile, JobReference lastReference) {

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
        this.interviewModel = interviewFile;
        this.status = new JobOpeningStatus();
        this.status.setStatusDescriptionAsUNFINISHED();
        this.rank = new Rank(newJobReference);
    }

    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
                      String streetName, String city, String district, String streetNumber, String zipcode, Integer numVacancies,
                      String description, RequirementSpecification requirementsFile, InterviewModel interviewFile, JobReference lastReference, RecruitmentProcess recruitmentProcess) {

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
        this.interviewModel = interviewFile;
        this.status = new JobOpeningStatus();
        this.status.setStatusDescriptionAsUNFINISHED();
        this.rank = new Rank(newJobReference);
        this.recruitmentProcess = recruitmentProcess;
    }

    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
                      Address address, Integer numVacancies, String description, RequirementSpecification requirementsFile,
                      JobReference lastReference, Set<Application> applications) {

        Preconditions.noneNull(function, description, address, lastReference, requirementsFile, contractTypeDenomination,
                workModeDenomination, numVacancies);

        JobReference newJobReference = generateNewSequencialJobReference(lastReference);
        this.jobReference = generateNewSequencialJobReference(lastReference);
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
        this.applications = applications;
    }

    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
                      String streetName, String city, String district, String streetNumber, String zipcode, Integer numVacancies,
                      String description, RequirementSpecification requirementsFile, JobReference lastReference, Set<Application> applications) {

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
        this.applications = applications;
    }

    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
                      Address address, Integer numVacancies, String description, RequirementSpecification requirementsFile,
                      JobReference lastReference, RecruitmentProcess recruitmentProcess) {

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
        this.recruitmentProcess = recruitmentProcess;
    }


    protected JobOpening() {
        //for ORM
    }

    private JobReference generateNewSequencialJobReference(JobReference lastReference) {
        return new JobReference(lastReference.getcustomerCode(), lastReference.getSequentialCode() + 1);
    }

    public JobReference jobReference() {
        return identity();
    }

    public JobOpeningStatus jobOpeningStatus() {
        return this.status;
    }

    public void updateStatusToStarted() {
        this.status = new JobOpeningStatus(JobOpeningStatusEnum.STARTED);
    }

    public void updateStatusToNotStarted() {
        this.status = new JobOpeningStatus(JobOpeningStatusEnum.NOT_STARTED);
    }


    public void addRecruitmentProcess(RecruitmentProcess recruitmentProcess) {
        this.recruitmentProcess = recruitmentProcess;
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

    public void changeRequirementSpecification(RequirementSpecification requirementSpecification) {
        Preconditions.noneNull(requirementSpecification);
        Preconditions.ensure(status.getStatusDescription().equals(String.valueOf(JobOpeningStatusEnum.UNFINISHED)) ||
                status.getStatusDescription().equals(String.valueOf(JobOpeningStatusEnum.NOT_STARTED)));

        this.requirementSpecification = requirementSpecification;
    }

    public void changeInterviewModel(InterviewModel interviewModel) {
        Preconditions.noneNull(interviewModel);
        if (!status.getStatusDescription().equals(String.valueOf(JobOpeningStatusEnum.UNFINISHED)) &&
                !status.getStatusDescription().equals(String.valueOf(JobOpeningStatusEnum.NOT_STARTED))) {

            throw new IllegalStateException("The interview model can only be changed if the status is UNFINISHED or NOT_STARTED");
        } else {
            this.interviewModel = interviewModel;
        }

    }

    public Boolean changeInformation(List<EditableInformation> selectedInformation, List<String> newInformation) {
        if (selectedInformation.size() != newInformation.size()) {
            throw new IllegalArgumentException("The number of selected information must match the number of new information provided.");
        }

        for (int i = 0; i < selectedInformation.size(); i++) {
            String selected = selectedInformation.get(i).toString();
            String newInfo = newInformation.get(i);

            EditableInformation info;
            try {
                info = EditableInformation.fromString(selected);
            } catch (IllegalArgumentException e) {
                return false;
            }

            if (info.equals(EditableInformation.ADDRESS)) {
                changeAddress(newInfo);
            } else if (info.equals(EditableInformation.DESCRIPTION)) {
                changeDescription(newInfo);
            } else if (info.equals(EditableInformation.FUNCTION)) {
                changeFunction(newInfo);
            } else if (info.equals(EditableInformation.NUM_VACANCIES)) {
                changeNumVacancies(newInfo);
            } else {
                return false;
            }
        }
        return true;
    }

    public void changeWorkMode(WorkMode newInfo) {
        this.workMode = newInfo;
    }

    private void changeNumVacancies(String newInfo) {
        this.numVacancies = NumberVacancy.valueOf(Integer.parseInt(newInfo));
    }

    private void changeFunction(String newInfo) {
        this.function = JobFunction.valueOf(newInfo);
    }

    private void changeDescription(String newInfo) {
        this.description = Description.valueOf(newInfo);
    }

    public void changeContractType(ContractType newInfo) {
        this.contractType = newInfo;
    }

    private void changeAddress(String newInfo) {
        if (status.equals(String.valueOf(JobOpeningStatusEnum.NOT_STARTED)) || status.equals(String.valueOf(JobOpeningStatusEnum.UNFINISHED))) {
            this.address = Address.valueOf(newInfo);
        } else {
            throw new IllegalArgumentException("Unable to change information.");
        }
    }

    @Override
    public JobOpeningDTO toDTO() {
        if (interviewModel == null) {
            return new JobOpeningDTO(address.toString(), function.jobFunction(), description.description(), status.getStatusDescription(),
                    contractType.getDenomination(), workMode.denomination(), numVacancies.getNumVacancies(),
                    requirementSpecification.requirementName().name(), jobReference.toString(), jobReference.getcustomerCode(), "");
        }
        return new JobOpeningDTO(address.toString(), function.jobFunction(), description.description(), status.getStatusDescription(),
                contractType.getDenomination(), workMode.denomination(), numVacancies.getNumVacancies(),
                requirementSpecification.requirementName().name(), jobReference.toString(), jobReference.getcustomerCode(), interviewModel.interviewModelName().name());
    }

    public List<EditableInformation> editableJobOpeningInformation() {
        String statusDescription = this.status.getStatusDescription();

        if ("UNFINISHED".equals(statusDescription) || "NOT_STARTED".equals(statusDescription)) {
            return EditableInformation.notStartedEditableInformation();
        }

        if ("STARTED".equals(statusDescription)) {
            String activePhase = recruitmentProcess.currentActivePhase().toLowerCase();
            switch (activePhase) {
                case "application":
                case "none active":
                    return EditableInformation.startedEditableInformation();
                case "screening":
                    return EditableInformation.screeningEditableInformation();
                default:
                    throw new IllegalStateException("It isn't possible to alter any information in the job opening");
            }
        }
        throw new IllegalStateException("It isn't possible to alter any information in the job opening");
    }

}
