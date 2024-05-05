package jobs4u.base.requirementsmanagement.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FullClassNameTest {

    @Test
    void ensureItHasAName() {
        assertThrows(IllegalArgumentException.class, () -> new FullClassName(null));
    }
}