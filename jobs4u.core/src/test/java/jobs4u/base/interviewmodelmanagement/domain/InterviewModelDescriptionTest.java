package jobs4u.base.interviewmodelmanagement.domain;

import jobs4u.base.languageenginnermanagement.interviewmodelmanagement.domain.InterviewModelDescription;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;

class InterviewModelDescriptionTest {

    @Test
    void ensureItHasADescription() {
        assertThrows(IllegalArgumentException.class, () -> new InterviewModelDescription(null));
    }
}