package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;

@Entity
public class JobOpening implements AggregateRoot<JobReference> {
    private String function;
    private ContractType contractType;
    private  WorkMode workMode;
    @EmbeddedId
    private JobReference jobReference = new JobReference();
    private Address address;
    private String description;
    private int numVacancies;
    private String requirementSpecification;


    public JobOpening(String function, ContractTypeDTO contractTypeDenomination, WorkModeDTO workModeDenomination,
                      String streetName, String city, String district, String state, int zipcode, int numVacancies,
                      String description, RequirementSpecification requirementsFile, JobReference lastReference) {

        this.jobReference = generateNewSequencialJobReference(lastReference);
        this.function = function;
        this.address = new Address(streetName, city, district, state, zipcode);
        this.contractType = ContractType.valueOf(contractTypeDenomination.toString());
        this.workMode = WorkMode.valueOf(workModeDenomination.toString());
        this.description = description;
        this.numVacancies = numVacancies;
        this.requirementSpecification = requirementsFile.getName().getName();
    }

    public JobOpening() {

    }

    private JobReference generateNewSequencialJobReference(JobReference lastReference) {
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public int compareTo(JobReference other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public JobReference identity() {
        return null;
    }
}
