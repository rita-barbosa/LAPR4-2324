package jobs4u.base.jobopeningmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;

public class ContractType implements DTOable<ContractTypeDTO>, ValueObject, AggregateRoot<String> {

    private String denomination;

    public ContractType(String denomination) {
        this.denomination = denomination;
    }

    public static ContractType valueOf(final String contractTypeDenomination) {
        Preconditions.noneNull(contractTypeDenomination);
        return new ContractType(contractTypeDenomination);
    }

    @Override
    public ContractTypeDTO toDTO() {
        return new ContractTypeDTO(denomination);
    }


    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public String identity() {
        return null;
    }
}
