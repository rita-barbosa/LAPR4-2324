package jobs4u.base.requirementsmanagement.domain;

import jobs4u.base.languageenginnermanagement.requirementsmanagement.domain.RequirementName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequirementNameTest {

    @Test
    void ensureItHasAName() {
        assertThrows(IllegalArgumentException.class, () -> new RequirementName(null));
    }
}