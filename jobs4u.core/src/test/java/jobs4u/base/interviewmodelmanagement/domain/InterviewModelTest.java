package jobs4u.base.interviewmodelmanagement.domain;

import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.requirementsmanagement.domain.PluginJarFile;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class InterviewModelTest {

    InterviewModelName name = new InterviewModelName("Prime Minister");
    InterviewModelDescription description = new InterviewModelDescription("Prime Ministers with experience in running a country.");
    PluginJarFile pluginJarFile = new PluginJarFile("Plugins/Interviews/Prime_Minister/Prime_Minister_Interview_Plugin.jar");

    @Test
    void ensureSameAsWorksAsExpected() {
        InterviewModelDescription description1 = new InterviewModelDescription("Back-End Developer With Experience in Java");
        InterviewModelName name1 = new InterviewModelName("Back-End Developer");
        PluginJarFile pluginJarFile1 = new PluginJarFile("Plugins/Interviews/Back_End_Dev/Back_End_Dev_Interview_Plugin.jar");
        InterviewModel interviewModel1 = new InterviewModel(name1, description1, pluginJarFile1);

        InterviewModelDescription description2 = new InterviewModelDescription("Front-End Developer With Experience in HTML");
        InterviewModelName name2 = new InterviewModelName("Front-End Developer");
        PluginJarFile pluginJarFile2 = new PluginJarFile("Plugins/Interviews/Front_End_Dev/Front_End_Dev_Interview_Plugin.jar");
        InterviewModel interviewModel2 = new InterviewModel(name2, description2, pluginJarFile2);

        InterviewModel interviewModel3 = new InterviewModel(name2, description2, pluginJarFile2);

        // Assert that the sameAs() method checks that two different interview models are different
        assertFalse(interviewModel1.sameAs(interviewModel2));

        // Assert that the sameAs() method checks that the same interview model is checked as being the same
        assertTrue(interviewModel1.sameAs(interviewModel1));

        // Assert that the sameAs() method checks that two different interview objects with the same value objects are considered the same
        assertTrue(interviewModel2.sameAs(interviewModel3));
    }

    @Test
    void ensureItHasAnInterviewModelName() {
        assertThrows(IllegalArgumentException.class, () -> new InterviewModel(null, description, pluginJarFile));
    }

    @Test
    void ensureItHasAnInterviewModelDescription() {
        assertThrows(IllegalArgumentException.class, () -> new InterviewModel(name, null, pluginJarFile));
    }

    @Test
    void ensureItHasAPluginJarFile() {
        assertThrows(IllegalArgumentException.class, () -> new InterviewModel(name, description, null));
    }

}