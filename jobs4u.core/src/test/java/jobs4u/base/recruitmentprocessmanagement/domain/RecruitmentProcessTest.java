package jobs4u.base.recruitmentprocessmanagement.domain;

import eapli.framework.time.util.Calendars;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecruitmentProcessTest {

    @Test
    void setPhases() {

        List<Phase> listA = new ArrayList<>();

        Calendar start = null;
        Calendar finish = null;

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        try {
            start = Calendars.fromDate(df.parse("16-03-2004"));
            finish = Calendars.fromDate(df.parse("18-03-2024"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        RecruitmentProcess recruitmentProcess = new RecruitmentProcess(start, finish, listA);

        Phase phase = new Phase("Test", "Test", "Test", start, finish);

        listA.add(phase);

        recruitmentProcess.setPhases(listA);

        assertTrue(recruitmentProcess.allPhases().contains(phase));
    }

    @Test
    void ensureItCantCreateWithNullParameter1() {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Calendar start = null;
        Calendar finish = null;
        List<Phase> listA = new ArrayList<>();
        try {
            start = Calendars.fromDate(df.parse("16-03-2004"));
            finish = Calendars.fromDate(df.parse("18-03-2024"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar finalFinish = finish;
        Calendar finalStart = start;
        assertThrows(IllegalArgumentException.class, () -> new RecruitmentProcess(null, finalFinish, listA));
    }

    @Test
    void ensureItCantCreateWithNullParameter2() {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Calendar start = null;
        Calendar finish = null;
        List<Phase> listA = new ArrayList<>();
        try {
            start = Calendars.fromDate(df.parse("16-03-2004"));
            finish = Calendars.fromDate(df.parse("18-03-2024"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar finalFinish = finish;
        Calendar finalStart = start;
        assertThrows(IllegalArgumentException.class, () -> new RecruitmentProcess(finalStart, null, listA));
    }

    @Test
    void ensureItCantCreateWithNullParameter3() {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Calendar start = null;
        Calendar finish = null;
        List<Phase> listA = new ArrayList<>();
        try {
            start = Calendars.fromDate(df.parse("16-03-2004"));
            finish = Calendars.fromDate(df.parse("18-03-2024"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar finalFinish = finish;
        Calendar finalStart = start;
        assertThrows(IllegalArgumentException.class, () -> new RecruitmentProcess(finalStart, finalFinish, null));
    }

    @Test
    void ensureItCantCreatePhaseWithSameEndAndStartDate() {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Calendar start = null;
        Calendar finish = null;
        List<Phase> phases = new ArrayList<>();
        try {
            start = Calendars.fromDate(df.parse("16-03-2024"));
            finish = Calendars.fromDate(df.parse("16-03-2024"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar finalFinish = finish;
        Calendar finalStart = start;
        assertThrows(IllegalArgumentException.class, () -> new RecruitmentProcess(finalStart, finalFinish, phases));

    }

    @Test
    void ensureItCantCreatePhaseWithStartDateAfterEndDate() {

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Calendar start = null;
        Calendar finish = null;
        List<Phase> phases = new ArrayList<>();
        try {
            start = Calendars.fromDate(df.parse("18-03-2024"));
            finish = Calendars.fromDate(df.parse("16-03-2024"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Calendar finalFinish = finish;
        Calendar finalStart = start;
        assertThrows(IllegalArgumentException.class, () -> new RecruitmentProcess(finalStart, finalFinish, phases));

    }


}