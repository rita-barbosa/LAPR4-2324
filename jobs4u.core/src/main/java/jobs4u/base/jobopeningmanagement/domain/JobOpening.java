package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.base.customermanagement.domain.CustomerCode;
import jobs4u.base.customermanagement.dto.CustomerDTO;
import jobs4u.base.jobopeningmanagement.domain.rank.Rank;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;

import java.util.Objects;

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
    @Enumerated(EnumType.STRING)
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

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Rank rank;
    @Transient
    private String company;
    @AttributeOverride(name = "value", column = @Column(name = "Company"))
    private CustomerCode customerCode;

    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
                      String streetName, String city, String district, String streetNumber, String zipcode, Integer numVacancies,
                      String description, RequirementSpecification requirementsFile, JobReference lastReference,
                      CustomerDTO companyInfo) {

        Preconditions.noneNull(function, description, district, streetNumber, lastReference, requirementsFile, zipcode,
                city, contractTypeDenomination, workModeDenomination, numVacancies, streetName, companyInfo);

        JobReference newJobReference = generateNewSequencialJobReference(lastReference);
        this.jobReference = newJobReference;
        this.function = JobFunction.valueOf(function);
        this.address = new Address(streetName, city, district, streetNumber, zipcode);
        this.contractType = ContractType.valueOf(contractTypeDenomination.contractTypeName());
        this.workMode = WorkMode.valueOf(workModeDenomination.workModeName());
        this.description = Description.valueOf(description);
        this.numVacancies = NumberVacancy.valueOf(numVacancies);
        this.requirementSpecification = requirementsFile;
        this.status = JobOpeningStatus.UNFINISHED;
        this.company = companyInfo.companyName();
        this.customerCode = CustomerCode.valueOf(companyInfo.customerCode());
        this.rank = new Rank(newJobReference);
    }

    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
                      Address address, Integer numVacancies, String description, RequirementSpecification requirementsFile,
                      JobReference lastReference) {

        Preconditions.noneNull(function, description, address, lastReference, requirementsFile, contractTypeDenomination,
                workModeDenomination, numVacancies);

        this.jobReference = generateNewSequencialJobReference(lastReference);
        this.function = JobFunction.valueOf(function);
        this.address = address;
        this.contractType = ContractType.valueOf(contractTypeDenomination.contractTypeName());
        this.workMode = WorkMode.valueOf(workModeDenomination.workModeName());
        this.description = Description.valueOf(description);
        this.numVacancies = NumberVacancy.valueOf(numVacancies);
        this.requirementSpecification = requirementsFile;
        this.status = JobOpeningStatus.UNFINISHED;
        //SEE RANK ATTRIBUTE
    }

    public JobOpening() {
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
        this.status = JobOpeningStatus.STARTED;
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
        return Objects.equals(version, that.version) && Objects.equals(function, that.function) && Objects.equals(contractType, that.contractType) && Objects.equals(workMode, that.workMode) && status == that.status && Objects.equals(jobReference, that.jobReference) && Objects.equals(address, that.address) && Objects.equals(description, that.description) && Objects.equals(numVacancies, that.numVacancies) && Objects.equals(requirementSpecification, that.requirementSpecification) && Objects.equals(rank, that.rank) && Objects.equals(company, that.company) && Objects.equals(customerCode, that.customerCode);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public JobOpeningDTO toDTO() {
        return new JobOpeningDTO(function.jobFunction(), contractType.getDenomination(), workMode.denomination(), jobReference.toString(), address.toString(), description.description(), numVacancies.numberVacancies(), company);
    }

    public void changeRequirementSpecification(RequirementSpecification requirementSpecification) {
        Preconditions.noneNull(requirementSpecification);
        Preconditions.ensure(status == JobOpeningStatus.UNFINISHED || status == JobOpeningStatus.NOT_STARTED);

        this.requirementSpecification = requirementSpecification;
    }
}
