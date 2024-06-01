package jobs4u.base.applicationmanagement.domain;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {
    RequirementAnswer requirementAnswer = new RequirementAnswer("plugins-config-file/requirement/r-answer-1.txt");
    RequirementResult requirementResult = new RequirementResult(true);
    Set<ApplicationFile> files = new HashSet<>();

    Date date = new Date(2024, Calendar.JANUARY, 5);
    Interview interview = new Interview("interview1", new Date(2024, Calendar.FEBRUARY, 2),
            new InterviewResult("correct", 50, "wrong"), "answer");


    @Test
    public void ensureMustHaveRequirementAnswer() {
        assertThrows(IllegalArgumentException.class, () -> new Application(null, requirementResult, files, date, interview));
    }

    @Test
    public void ensureMustHaveRequirementResult() {
        assertThrows(IllegalArgumentException.class, () -> new Application(requirementAnswer, null, files, date, interview));
    }
    @Test
    public void ensureMustHaveFile() {
        assertThrows(IllegalArgumentException.class, () -> new Application(requirementAnswer, requirementResult, null, date, interview));
    }

    @Test
    public void ensureMustHaveDate() {
        assertThrows(IllegalArgumentException.class, () -> new Application(requirementAnswer, requirementResult, files, null, interview));
    }


}