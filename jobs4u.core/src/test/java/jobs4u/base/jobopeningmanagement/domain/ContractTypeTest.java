package jobs4u.base.jobopeningmanagement.domain;

import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class ContractTypeTest {

    ContractTypeDTO contractType = new ContractTypeDTO("full-time");

    @Test
    public void ensureContractTypeIsValid() {
        assertThrows(IllegalArgumentException.class, () -> new ContractType(null));
        assertThrows(IllegalArgumentException.class, () -> new ContractType(""));
        assertEquals("full-time", contractType.contractTypeName());
    }

}