package jobs4u.base.jobopeningmanagement.dto;

public class ContractTypeDTO {

    private final String contractTypeName;

    public ContractTypeDTO(String denomination) {
        this.contractTypeName = denomination;
    }

    public String contractTypeName(){
        return contractTypeName;
    }

    @Override
    public String toString() {
        return contractTypeName;
    }
}
