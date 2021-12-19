package ru.learnup.javaqa.learnupmvn2.steps;

import org.junit.jupiter.api.Test;
import ru.learnup.javaqa.learnupmvn2.exception.*;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StepsManagerTest {

    private String mapToString(StepsManager manager){
        return manager.getStats().toString();
    }

    private String streamToString(Stream<Integer> stream) {
        return Arrays.toString(stream.toArray());
    }

    //add tests

    @Test
    public void addInit(){
        StepsManager stepsManager = new StepsManager();
        assertEquals("{}", mapToString(stepsManager));
    }

    @Test
    public void addSimple(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 11);
        stepsManager.add(365, 32);
        assertEquals("{1=10, 3=11, 365=32}", mapToString(stepsManager));
    }

    @Test
    public void addRewrite(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(365, 1);
        stepsManager.add(365, 12);
        stepsManager.add(365, 19);
        assertEquals("{1=10, 3=33, 365=32}", mapToString(stepsManager));
    }

    @Test
    public void addDayNegative(){
        StepsManager stepsManager = new StepsManager();
        assertThrows(IllegalDayException.class, () -> stepsManager.add(-1, 10));
    }

    @Test
    public void addDayZero(){
        StepsManager stepsManager = new StepsManager();
        assertThrows(IllegalDayException.class, () -> stepsManager.add(0, 10));
    }

    @Test
    public void addDayTooLarge(){
        StepsManager stepsManager = new StepsManager();
        assertThrows(IllegalDayException.class, () -> stepsManager.add(366, 10));
    }

    @Test
    public void addStepsNegative(){
        StepsManager stepsManager = new StepsManager();
        assertThrows(IllegalStepsException.class, () -> stepsManager.add(1, -10));
    }

    @Test
    public void addStepsZero(){
        StepsManager stepsManager = new StepsManager();
        assertThrows(IllegalStepsException.class, () -> stepsManager.add(1, 0));
    }

    @Test
    public void addNoOverflow(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(3, Integer.MAX_VALUE - 1000);
        stepsManager.add(3, 323122);
        stepsManager.add(3, Integer.MAX_VALUE);

        assertEquals("{3=" + Integer.MAX_VALUE + "}", mapToString(stepsManager));
    }

    //stepsRecord tests
    @Test
    public void stepsRecordInit(){
        StepsManager stepsManager = new StepsManager();
        assertEquals(0, stepsManager.getStepsRecord());
    }

    @Test
    public void stepsRecordSimple(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(365, 1);
        assertEquals(33, stepsManager.getStepsRecord());
    }

    @Test
    public void stepsRecordRewrite(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(365, 23);
        stepsManager.add(365, 11);
        stepsManager.add(3, 1);
        stepsManager.add(3, 12);
        stepsManager.add(3, 19);
        assertEquals(65, stepsManager.getStepsRecord());
    }

    @Test
    public void stepsRecordMax(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(3, Integer.MAX_VALUE - 100);
        stepsManager.add(3, 32312);
        stepsManager.add(3, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, stepsManager.getStepsRecord());
    }

    @Test
    public void stepsRecordNoOverflow(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(3, Integer.MAX_VALUE - 100);
        stepsManager.add(3, 32312);
        stepsManager.add(3, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, stepsManager.remainder(1));
    }

    //remainder tests
    @Test
    public void remainderInit(){
        StepsManager stepsManager = new StepsManager();
        assertEquals(1, stepsManager.remainder(1));
    }

    @Test
    public void remainderSimple(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(365, 1);
        assertEquals(24, stepsManager.remainder(1));
    }

    @Test
    public void remainderRewrite(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(365, 23);
        stepsManager.add(365, 11);
        stepsManager.add(3, 1);
        stepsManager.add(3, 12);
        stepsManager.add(3, 19);//65
        assertEquals(56, stepsManager.remainder(1)); //65 - 10 + 1 = 56
    }

    @Test
    public void remainderNewRecord(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(365, 34);
        stepsManager.add(3, 32); //65
        assertEquals(1, stepsManager.remainder(3));
    }

    @Test
    public void remainderEmptyDay(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(365, 23);
        stepsManager.add(365, 11);
        stepsManager.add(3, 1);
        stepsManager.add(3, 12);
        stepsManager.add(3, 19);//65
        assertEquals(66, stepsManager.remainder(6));
    }

    @Test
    public void remainderDayNegative(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(365, 1);
        assertEquals(-1, stepsManager.remainder(-1));
    }

    @Test
    public void remainderDayZero(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(365, 1);
        assertEquals(-1, stepsManager.remainder(0));
    }

    @Test
    public void remainderDayTooLarge(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(365, 1);
        assertEquals(-1, stepsManager.remainder(366));
    }

    //compareTo tests

    @Test
    public void compareToInit(){
        StepsManager p1 = new StepsManager();
        StepsManager p2 = new StepsManager();
        assertEquals(0, p1.compareTo(p2));
    }

    @Test
    public void compareP1(){
        StepsManager p1 = new StepsManager();
        StepsManager p2 = new StepsManager();
        p1.add(3, 7);
        p1.add(3, 7);
        p2.add(1, 1);
        p2.add(365, 1);
        p2.add(32, 3);
        assertTrue(p1.compareTo(p2) > 0);
    }

    @Test
    public void compareP2(){
        StepsManager p1 = new StepsManager();
        StepsManager p2 = new StepsManager();
        p1.add(3, 7);
        p1.add(3, 7);
        p2.add(1, 1);
        p2.add(365, 100);
        p2.add(32, 3);
        assertTrue(p2.compareTo(p1) > 0);
    }

    @Test
    public void compareDraw(){
        StepsManager p1 = new StepsManager();
        StepsManager p2 = new StepsManager();
        p1.add(3, 1);
        p1.add(3, 2);
        p1.add(3, 3);
        p2.add(365, 1);
        p2.add(32, 2);
        p2.add(1, 3);
        assertEquals(0, p1.compareTo(p2));
    }

    @Test
    public void compareNoOverflow(){
        StepsManager p1 = new StepsManager();
        StepsManager p2 = new StepsManager();
        p1.add(3, Integer.MAX_VALUE);
        p1.add(3, 10);
        p1.add(3, Integer.MAX_VALUE);
        p2.add(365, Integer.MAX_VALUE);
        p2.add(32, Integer.MAX_VALUE);
        p2.add(1, Integer.MAX_VALUE);
        assertTrue(p1.compareTo(p2) < 0);
    }

    //getAllAbove tests

    @Test
    public void getAllAboveInit(){
        StepsManager stepsManager = new StepsManager();
        assertEquals("[]", streamToString(stepsManager.getAllAbove(10)));
    }

    @Test
    public void getAllAboveSimple(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 11);
        stepsManager.add(365, 32);
        assertEquals("[3, 365]", streamToString(stepsManager.getAllAbove(10)));
    }

    @Test
    public void getAllAboveRewrite(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 1);
        stepsManager.add(1, 8);
        stepsManager.add(3, 1);
        stepsManager.add(3, 10);
        stepsManager.add(365, 32);
        stepsManager.add(365, 32);
        assertEquals("[3, 365]", streamToString(stepsManager.getAllAbove(10)));
    }

    @Test
    public void getAllAboveEmpty(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 11);
        stepsManager.add(365, 32);
        assertEquals("[]", streamToString(stepsManager.getAllAbove(100)));
    }

    @Test
    public void getAllAboveStepsNegative(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 11);
        stepsManager.add(365, 32);
        assertEquals("[1, 3, 365]", streamToString(stepsManager.getAllAbove(-100)));
    }

    @Test
    public void getAllAboveStepsZero(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 1);
        stepsManager.add(3, 11);
        stepsManager.add(365, 1);
        assertEquals("[1, 3, 365]", streamToString(stepsManager.getAllAbove(0)));
    }

    @Test
    public void getAllAboveMax(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 11);
        stepsManager.add(365, Integer.MAX_VALUE);
        assertEquals("[]", streamToString(stepsManager.getAllAbove(Integer.MAX_VALUE)));
    }

}