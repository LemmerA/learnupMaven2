package ru.learnup.javaqa.learnupmvn2.jdbc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.learnup.javaqa.learnupmvn2.exception.IllegalDayException;
import ru.learnup.javaqa.learnupmvn2.exception.IllegalStepsException;
import ru.learnup.javaqa.learnupmvn2.jdbc.entities.Steps;
import ru.learnup.javaqa.learnupmvn2.jdbc.entities.StepsLog;

import static org.junit.jupiter.api.Assertions.*;

class DbHelperTest {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/learnup";
    private static final String DB_USER = "postgres";
    private static final String DB_PASS = "postgres";

    DbHelper db = new DbHelper(DB_URL, DB_USER, DB_PASS);

    @Disabled
    @Test
    public void getStepsAll() {
        for (Steps steps : db.getAllSteps()){
            assertDoesNotThrow(() -> System.out.println(steps.toString()));
        }
    }

    @Disabled
    @Test
    public void getStepsLogAll() {
        for (StepsLog stepsLog : db.getAllStepsLog()){
            assertDoesNotThrow(() -> System.out.println(stepsLog.toString()));
        }
    }

    @Disabled
    @Test
    public void addStepsSimple() {
        assertEquals(1, db.addSteps(new StepsLog((int)(Math.random()*(365-1+1)+1),
                (int)(Math.random()*Integer.MAX_VALUE)+1)));
    }

    @Disabled
    @Test
    public void addStepsRewrite() {
        assertEquals(1, db.addSteps(new StepsLog(10, (int)(Math.random()*1000)+1)));
        assertEquals(1, db.addSteps(new StepsLog(10, (int)(Math.random()*1000)+1)));
        assertEquals(1, db.addSteps(new StepsLog(10, (int)(Math.random()*1000)+1)));
        assertEquals(1, db.addSteps(new StepsLog(10, (int)(Math.random()*1000)+1)));
        assertEquals(1, db.addSteps(new StepsLog(10, (int)(Math.random()*1000)+1)));
    }

    @Disabled
    @Test
    public void addStepsNoOverflow() {
        assertEquals(1, db.addSteps(new StepsLog(100, 10)));
        assertEquals(1, db.addSteps(new StepsLog(100, Integer.MAX_VALUE)));
        assertEquals(1, db.addSteps(new StepsLog(100, Integer.MAX_VALUE)));
    }

    @Test
    public void addDayNegative(){
        assertThrows(IllegalDayException.class, () -> db.addSteps(new StepsLog(-1, 23)));
    }

    @Test
    public void addDayZero(){
        assertThrows(IllegalDayException.class, () -> db.addSteps(new StepsLog(0, 23)));
    }

    @Test
    public void addDayTooLarge(){
        assertThrows(IllegalDayException.class, () -> db.addSteps(new StepsLog(366, 23)));
    }

    @Test
    public void addStepsNegative(){
        assertThrows(IllegalStepsException.class, () -> db.addSteps(new StepsLog(1, -23)));
    }

    @Test
    public void addStepsZero(){
        assertThrows(IllegalStepsException.class, () -> db.addSteps(new StepsLog(1, 0)));
    }

}