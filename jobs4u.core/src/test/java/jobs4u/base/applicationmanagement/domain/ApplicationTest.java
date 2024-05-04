package jobs4u.base.applicationmanagement.domain;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    RequirementAnswer requirementAnswer = new RequirementAnswer("The requirement was complete!");
    RequirementResult requirementResult = new RequirementResult(true);
    File file = new File("example.txt");
    Date date = new Date(2024, Calendar.JANUARY, 5);
    Interview interview = new Interview("interview1", new Date(2024, Calendar.FEBRUARY, 2),
            new InterviewResult("correct", 50, "wrong"), "answer");



    @Test
    public void ensureMustHaveRequirementAnswer() {
        assertThrows(IllegalArgumentException.class, () -> new Application(null, requirementResult, file, date, interview));
    }

    @Test
    public void ensureMustHaveRequirementResult() {
        assertThrows(IllegalArgumentException.class, () -> new Application(requirementAnswer, null, file, date, interview));
    }
    @Test
    public void ensureMustHaveFile() {
        assertThrows(IllegalArgumentException.class, () -> new Application(requirementAnswer, requirementResult, null, date, interview));
    }

    @Test
    public void ensureMustHaveDate() {
        assertThrows(IllegalArgumentException.class, () -> new Application(requirementAnswer, requirementResult, file, null, interview));
    }


}