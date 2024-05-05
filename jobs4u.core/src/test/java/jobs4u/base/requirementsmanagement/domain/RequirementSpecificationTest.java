package jobs4u.base.requirementsmanagement.domain;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.FullClassName;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementDescription;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementName;
import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class RequirementSpecificationTest {

    String name = "Prime Minister";
    String description = "Prime Ministers with experience in running a country.";
    String fullClassName = "Plugins/Requirements/Prime_Minister/Prime_Minister_Requirement_Plugin.jar";

    @Test
    void ensureSameAsWorksAsExpected() {
        String description1 = "Back-End Developer With Experience in Java";
        String name1 = "Back-End Developer";
        String fullClassName1 = "Plugins/Requirements/Back_End_Dev/Back_End_Dev_Requirement_Plugin.jar";
        RequirementSpecification requirementSpecification1 = new RequirementSpecification(name1, description1, fullClassName1);

        String description2 = "Front-End Developer With Experience in HTML";
        String name2 = "Front-End Developer";
        String fullClassName2 = "Plugins/Requirements/Front_End_Dev/Front_End_Dev_Requirement_Plugin.jar";
        RequirementSpecification requirementSpecification2 = new RequirementSpecification(name2, description2, fullClassName2);

        RequirementSpecification requirementSpecification3 = new RequirementSpecification(name2, description2, fullClassName2);

        // Assert that the sameAs() method checks that two different interview models are different
        assertFalse(requirementSpecification1.sameAs(requirementSpecification2));

        // Assert that the sameAs() method checks that the same interview model is checked as being the same
        assertTrue(requirementSpecification1.sameAs(requirementSpecification1));

        // Assert that the sameAs() method checks that two different interview objects with the same value objects are considered the same
        assertTrue(requirementSpecification2.sameAs(requirementSpecification3));
    }

    @Test
    void ensureItHasAnRequirementName() {
        assertThrows(IllegalArgumentException.class, () -> new RequirementSpecification(null, description, fullClassName));
    }

    @Test
    void ensureItHasAnRequirementDescription() {
        assertThrows(IllegalArgumentException.class, () -> new RequirementSpecification(name, null, fullClassName));
    }

    @Test
    void ensureItHasAPluginJarFile() {
        assertThrows(IllegalArgumentException.class, () -> new RequirementSpecification(name, description, null));
    }
}