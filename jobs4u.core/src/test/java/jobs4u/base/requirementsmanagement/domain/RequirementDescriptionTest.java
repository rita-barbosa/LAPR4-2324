package jobs4u.base.requirementsmanagement.domain;

import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementDescription;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequirementDescriptionTest {

    @Test
    void ensureItHasADescription() {
        assertThrows(IllegalArgumentException.class, () -> new RequirementDescription(null));
    }
}