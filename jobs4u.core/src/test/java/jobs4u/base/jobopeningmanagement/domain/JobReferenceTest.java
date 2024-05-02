package jobs4u.base.jobopeningmanagement.domain;

import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;
import jobs4u.base.requirementsmanagement.domain.PluginJarFile;
import jobs4u.base.requirementsmanagement.domain.RequirementDescription;
import jobs4u.base.requirementsmanagement.domain.RequirementName;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobReferenceTest {

    @Test
    public void ensureCustomerCodeNullIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new JobReference(null, 2));
    }

    @Test
    public void ensureCustomerCodeEmptyIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new JobReference("", 2));
    }

    @Test
    public void ensureSequentialCodeNullIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new JobReference("ISEP", null));
    }

    @Test
    public void ensureSequentialCodeNegativeIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new JobReference("ISEP", -1));
    }

    @Test
    public void ensureSequentialCodeZeroIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new JobReference("ISEP", 0));
    }

    @Test
    public void ensureJobReferenceIsSequential() {
        WorkModeDTO workMode = new WorkModeDTO("remote");
        ContractTypeDTO contractType = new ContractTypeDTO("full-time");
        JobReference jobReference = new JobReference("ISEP", 3);
        Address address = new Address("streetName", "city", "district", "14th",
                "12345");

        RequirementName name = new RequirementName("Senior Developer");
        RequirementDescription description = new RequirementDescription("Database maintenance");
        PluginJarFile pluginJarFile = new PluginJarFile("senior-developer-database.jar");
        RequirementSpecification requirementSpecification = new RequirementSpecification(name, description, pluginJarFile);

        assertEquals(4, new JobOpening("Senior Dev", contractType, workMode, address,
                15, "description", requirementSpecification,
                jobReference).jobReference().getSequentialCode());
    }


}