package jobs4u.base.requirementsmanagement.domain;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class RequirementSpecificationTest {

    RequirementName name = new RequirementName("Prime Minister");
    RequirementDescription description = new RequirementDescription("Prime Ministers with experience in running a country.");
    PluginJarFile pluginJarFile = new PluginJarFile("Plugins/Requirements/Prime_Minister/Prime_Minister_Requirement_Plugin.jar");

    @Test
    void ensureSameAsWorksAsExpected() {
        RequirementDescription description1 = new RequirementDescription("Back-End Developer With Experience in Java");
        RequirementName name1 = new RequirementName("Back-End Developer");
        PluginJarFile pluginJarFile1 = new PluginJarFile("Plugins/Requirements/Back_End_Dev/Back_End_Dev_Requirement_Plugin.jar");
        RequirementSpecification interviewModel1 = new RequirementSpecification(name1, description1, pluginJarFile1);

        RequirementDescription description2 = new RequirementDescription("Front-End Developer With Experience in HTML");
        RequirementName name2 = new RequirementName("Front-End Developer");
        PluginJarFile pluginJarFile2 = new PluginJarFile("Plugins/Requirements/Front_End_Dev/Front_End_Dev_Requirement_Plugin.jar");
        RequirementSpecification interviewModel2 = new RequirementSpecification(name2, description2, pluginJarFile2);

        RequirementSpecification interviewModel3 = new RequirementSpecification(name2, description2, pluginJarFile2);

        // Assert that the sameAs() method checks that two different interview models are different
        assertFalse(interviewModel1.sameAs(interviewModel2));

        // Assert that the sameAs() method checks that the same interview model is checked as being the same
        assertTrue(interviewModel1.sameAs(interviewModel1));

        // Assert that the sameAs() method checks that two different interview objects with the same value objects are considered the same
        assertTrue(interviewModel2.sameAs(interviewModel3));
    }

    @Test
    void ensureItHasAnRequirementName() {
        assertThrows(IllegalArgumentException.class, () -> new RequirementSpecification(null, description, pluginJarFile));
    }

    @Test
    void ensureItHasAnRequirementDescription() {
        assertThrows(IllegalArgumentException.class, () -> new RequirementSpecification(name, null, pluginJarFile));
    }

    @Test
    void ensureItHasAPluginJarFile() {
        assertThrows(IllegalArgumentException.class, () -> new RequirementSpecification(name, description, null));
    }
}