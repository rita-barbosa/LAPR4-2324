package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;

@Entity
@Table(name = "T_CONTRACTTYPE")
public class ContractType implements DTOable<ContractTypeDTO>, AggregateRoot<String> {

    @Id
    private String denomination;

    public ContractType(String denomination) {
        this.denomination = denomination;
    }

    protected ContractType() {
        // Required by JPA
    }

    public static ContractType valueOf(String contractTypeDenomination) {
        Preconditions.nonEmpty(contractTypeDenomination, "Contract type denomination must not be empty");
        return new ContractType(contractTypeDenomination);
    }

    public String getDenomination() {
        return denomination;
    }

    @Override
    public ContractTypeDTO toDTO() {
        return new ContractTypeDTO(denomination);
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ContractType that = (ContractType) other;
        return denomination.equals(that.denomination);
    }

    @Override
    public String identity() {
        return denomination;
    }
}