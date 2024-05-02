package jobs4u.base.jobopeningmanagement.domain;

import jobs4u.base.jobopeningmanagement.dto.WorkModeDTO;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class WorkModeTest {

    WorkModeDTO workMode = new WorkModeDTO("remote");

    @Test
    public void ensureWorkModeIsValid() {
        assertThrows(IllegalArgumentException.class, () -> new WorkMode(null));
        assertThrows(IllegalArgumentException.class, () -> new WorkMode(""));
        assertEquals("remote", workMode.workModeName());
    }

}