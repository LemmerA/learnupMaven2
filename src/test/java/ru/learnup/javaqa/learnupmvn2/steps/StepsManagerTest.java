package ru.learnup.javaqa.learnupmvn2.steps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StepsManagerTest {

    //add tests
    @Test
    public void addInit(){
        StepsManager stepsManager = new StepsManager();
        assertEquals("{}", stepsManager.getStats().toString());
    }

    @Test
    public void addSimple(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 11);
        stepsManager.add(78, 32);
        assertEquals("{1=10, 3=11, 78=32}", stepsManager.getStats().toString());
    }

    @Test
    public void addRewrite(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(78, 23);
        stepsManager.add(78, 11);
        stepsManager.add(3, 1);
        stepsManager.add(3, 12);
        stepsManager.add(3, 19);
        assertEquals("{1=10, 3=65, 78=34}", stepsManager.getStats().toString());
    }

    @Test
    public void addNegativeDay(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(-1, 10);
        stepsManager.add(0, 33);
        stepsManager.add(-78, 34);
        stepsManager.add(Integer.MIN_VALUE, 32);
        assertEquals("{}", stepsManager.getStats().toString());
    }

    @Test
    public void addNegativeSteps(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, -10);
        stepsManager.add(3, 0);
        stepsManager.add(78, -34);
        stepsManager.add(3, Integer.MIN_VALUE);
        assertEquals("{}", stepsManager.getStats().toString());
    }

    @Test
    public void addNoOverflow(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(3, Integer.MAX_VALUE - 1000);
        stepsManager.add(3, 323122);
        stepsManager.add(3, Integer.MAX_VALUE);

        assertEquals("{3=" + Integer.MAX_VALUE + "}", stepsManager.getStats().toString());
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
        stepsManager.add(78, 1);
        assertEquals(33, stepsManager.getStepsRecord());
    }

    @Test
    public void stepsRecordRewrite(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(78, 23);
        stepsManager.add(78, 11);
        stepsManager.add(3, 1);
        stepsManager.add(3, 12);
        stepsManager.add(3, 19);
        assertEquals(65, stepsManager.getStepsRecord());
    }

    @Test
    public void stepsRecordNegative(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(-1, 10);
        stepsManager.add(3, -33);
        stepsManager.add(0, 34);
        stepsManager.add(3, 0);
        assertEquals(0, stepsManager.getStepsRecord());
    }

    @Test
    public void stepsRecordMax(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(3, Integer.MAX_VALUE - 100);
        stepsManager.add(3, 32312);
        stepsManager.add(3, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, stepsManager.getStepsRecord());
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
        stepsManager.add(78, 1);
        assertEquals(24, stepsManager.remainder(1));
    }

    @Test
    public void remainderRewrite(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(78, 23);
        stepsManager.add(78, 11);
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
        stepsManager.add(78, 34);
        stepsManager.add(3, 32); //65
        assertEquals(1, stepsManager.remainder(3));
    }

    @Test
    public void remainderEmptyDay(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(78, 23);
        stepsManager.add(78, 11);
        stepsManager.add(3, 1);
        stepsManager.add(3, 12);
        stepsManager.add(3, 19);//65
        assertEquals(66, stepsManager.remainder(6));
    }

    @Test
    public void remainderNegativeDay(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(78, 1);
        assertEquals(-1, stepsManager.remainder(-1));
    }

    @Test
    public void remainderZeroDay(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(78, 1);
        assertEquals(-1, stepsManager.remainder(0));
    }

    @Test
    public void remainderMaxDay(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(1, 10);
        stepsManager.add(3, 33);
        stepsManager.add(Integer.MAX_VALUE, 1);
        assertEquals(33, stepsManager.remainder(Integer.MAX_VALUE));
    }

    @Test
    public void stepsRecordNoOverflow(){
        StepsManager stepsManager = new StepsManager();
        stepsManager.add(3, Integer.MAX_VALUE - 100);
        stepsManager.add(3, 32312);
        stepsManager.add(3, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, stepsManager.remainder(1));
    }
}