package ru.learnup.javaqa.learnupmvn2.jdbc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.learnup.javaqa.learnupmvn2.exception.IllegalDayException;
import ru.learnup.javaqa.learnupmvn2.exception.IllegalStepsException;
import ru.learnup.javaqa.learnupmvn2.jdbc.entities.Steps;
import ru.learnup.javaqa.learnupmvn2.jdbc.entities.StepsLog;

import static org.junit.jupiter.api.Assertions.*;

class DbHelperTest {
//
//    DbHelper db = new DbHelper();
//
//    @Test
//    public void getStepsAll() {
//        for (Steps steps : db.getAllSteps()){
//            assertDoesNotThrow(() -> System.out.println(steps.toString()));
//        }
//    }
//
//    @Test
//    public void getStepsLogAll() {
//        for (StepsLog stepsLog : db.getAllStepsLog()){
//            assertDoesNotThrow(() -> System.out.println(stepsLog.toString()));
//        }
//    }
//
//    @Test
//    public void addStepsSimple() {
//        assertDoesNotThrow(() -> db.addSteps(new StepsLog((int)(Math.random()*(365-1+1)+1),
//                (int)(Math.random()*Integer.MAX_VALUE)+1)));
//    }
//
//    @Test
//    public void addStepsRewrite() {
//        assertDoesNotThrow(() -> db.addSteps(new StepsLog(10, (int)(Math.random()*1000)+1)));
//        assertDoesNotThrow(() -> db.addSteps(new StepsLog(10, (int)(Math.random()*1000)+1)));
//        assertDoesNotThrow(() -> db.addSteps(new StepsLog(10, (int)(Math.random()*1000)+1)));
//        assertDoesNotThrow(() -> db.addSteps(new StepsLog(10, (int)(Math.random()*1000)+1)));
//        assertDoesNotThrow(() -> db.addSteps(new StepsLog(10, (int)(Math.random()*1000)+1)));
//    }
//
//    @Test
//    public void addStepsNoOverflow() {
//        assertDoesNotThrow(() -> db.addSteps(new StepsLog(100, 10)));
//        assertDoesNotThrow(() -> db.addSteps(new StepsLog(100, Integer.MAX_VALUE)));
//        assertDoesNotThrow(() -> db.addSteps(new StepsLog(100, Integer.MAX_VALUE)));
//    }
//
//    @Test
//    public void addDayNegative(){
//        assertThrows(IllegalDayException.class, () -> db.addSteps(new StepsLog(-1, 23)));
//    }
//
//    @Test
//    public void addDayZero(){
//        assertThrows(IllegalDayException.class, () -> db.addSteps(new StepsLog(0, 23)));
//    }
//
//    @Test
//    public void addDayTooLarge(){
//        assertThrows(IllegalDayException.class, () -> db.addSteps(new StepsLog(366, 23)));
//    }
//
//    @Test
//    public void addStepsNegative(){
//        assertThrows(IllegalStepsException.class, () -> db.addSteps(new StepsLog(1, -23)));
//    }
//
//    @Test
//    public void addStepsZero(){
//        assertThrows(IllegalStepsException.class, () -> db.addSteps(new StepsLog(1, 0)));
//    }

}