package jobs4u.base.jobopeningmanagement.domain;

import jobs4u.base.jobopeningmanagement.dto.ContractTypeDTO;
import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;
import jobs4u.base.requirementsmanagement.domain.PluginJarFile;
import jobs4u.base.requirementsmanagement.domain.RequirementDescription;
import jobs4u.base.requirementsmanagement.domain.RequirementName;
import jobs4u.base.requirementsmanagement.domain.RequirementSpecification;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class JobOpeningTest {

    WorkModeDTO workMode = new WorkModeDTO("remote");
    ContractTypeDTO contractType = new ContractTypeDTO("full-time");
    JobReference jobReference = new JobReference("ISEP", 3);
    String streetName = "StreetName";
    String city = "City";
    String district = "District";
    String streetNumber = "14th";
    String zipcode = "12345";
    Address address = new Address(streetName, city, district, streetNumber, zipcode);
    
    public RequirementSpecification jobOpeningRequirement(){
        RequirementName name = new RequirementName("Senior Developer");
        RequirementDescription description = new RequirementDescription("Database maintenance");
        PluginJarFile pluginJarFile = new PluginJarFile("senior-developer-database.jar");
        return new RequirementSpecification(name, description, pluginJarFile);
    }

    @Test
    public void ensureMustHaveTitle(){
        assertThrows(IllegalArgumentException.class, () -> new JobOpening(null, contractType, workMode,
                address, 15, "description", jobOpeningRequirement(), jobReference)
        );
    }

    @Test
    public void ensureMustHaveContractType(){
        assertThrows(IllegalArgumentException.class, () -> new JobOpening("function", null, workMode,
                address, 15, "description", jobOpeningRequirement(), jobReference)
        );
    }

    @Test
    public void ensureMustHaveWorkMode(){
        assertThrows(IllegalArgumentException.class, () -> new JobOpening("function", contractType, null,
                address, 15, "description", jobOpeningRequirement(), jobReference)
        );
    }

    @Test
    public void ensureMustHaveAddress(){
        assertThrows(IllegalArgumentException.class, () -> new JobOpening("function", contractType, workMode,
                null, 15, "description", jobOpeningRequirement(), jobReference)
        );
    }

    @Test
    public void ensureMustHaveNumberVacancies(){
        assertThrows(IllegalArgumentException.class, () -> new JobOpening("function", contractType, workMode,
                address, null, "description", jobOpeningRequirement(), jobReference)
        );
    }

    @Test
    public void ensureMustHaveDescription(){
        assertThrows(IllegalArgumentException.class, () -> new JobOpening("function", contractType, workMode,
                address, 15, null, jobOpeningRequirement(), jobReference)
        );
    }

    @Test
    public void ensureMustHaveRequirements(){
        assertThrows(IllegalArgumentException.class, () -> new JobOpening("function", contractType, workMode,
                address, 15, "description", null, jobReference)
        );
    }

    @Test
    public void ensureMustHaveLastJobReference(){
        assertThrows(IllegalArgumentException.class, () -> new JobOpening("function", contractType, workMode,
                address, 15, "description", jobOpeningRequirement(), null)
        );
    }

    @Test
    public void ensureHasGivenIdentity() {
        JobOpening opening =  new JobOpening("Senior Dev", contractType, workMode, address, 15,
                "description", jobOpeningRequirement(), jobReference);

        JobReference jobReference1 = new JobReference("ISEP", 4);
        assertEquals(opening.identity(), jobReference1);
    }

}