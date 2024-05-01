package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;

@Entity
@Table(name = "T_JOBOPENING")
public class JobOpening implements AggregateRoot<JobReference> {

    private static final long serialVersionUID = 1L;
    @Version
    private Long version;
    private JobFunction function;
    private ContractType contractType;
    private  WorkMode workMode;
    @Enumerated(EnumType.STRING)
    private JobOpeningStatus status;
    @EmbeddedId
    private JobReference jobReference = new JobReference();
    private Address address;
    private Description description;
    private NumberVacancy numVacancies;
    @ManyToOne
    private RequirementSpecification requirementSpecification;
    private Rank rank;


    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
                      String streetName, String city, String district, String streetNumber, String zipcode, int numVacancies,
                      String description, RequirementSpecification requirementsFile, JobReference lastReference) {

        Preconditions.noneNull(function, description, district, streetNumber, lastReference, requirementsFile, zipcode,
                city, contractTypeDenomination, workModeDenomination, numVacancies, streetName);
        Preconditions.nonEmpty(function);
        Preconditions.nonEmpty(description);
        Preconditions.nonEmpty(district);
        Preconditions.nonEmpty(streetName);
        Preconditions.nonEmpty(streetNumber);
        Preconditions.nonEmpty(city);
        Preconditions.nonEmpty(zipcode);

        this.jobReference = generateNewSequencialJobReference(lastReference);
        this.function = JobFunction.valueOf(function);
        this.address = new Address(streetName, city, district, streetNumber, zipcode);
        this.contractType = ContractType.valueOf(contractTypeDenomination.toString());
        this.workMode = WorkMode.valueOf(workModeDenomination.toString());
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
        return new JobReference(lastReference.getCostumerCode(), lastReference.getSequentialCode()+1);
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
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }


}
