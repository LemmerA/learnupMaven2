package ru.learnup.javaqa.learnupmvn2.steps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StepsManagerComparatorTest {

    StepsManagerComparator comp = new StepsManagerComparator(10);

    @Test
    public void stepsCompInit(){
        StepsManager p1 = new StepsManager();
        StepsManager p2 = new StepsManager();
        assertEquals(0, comp.compare(p1, p2));
    }

    @Test
    public void stepsCompP1(){
        StepsManager p1 = new StepsManager();
        StepsManager p2 = new StepsManager();
        p1. add(1, 100);
        p1. add(2, 11);
        p1. add(3, 10);
        p2. add(1, 10);
        p2. add(2, 10);
        p2. add(3, 90);
        assertTrue(comp.compare(p1, p2) > 0);
    }

    @Test
    public void stepsCompP2(){
        StepsManager p1 = new StepsManager();
        StepsManager p2 = new StepsManager();
        p1. add(1, 10);
        p1. add(2, 11);
        p1. add(3, 10);
        p2. add(1, 10);
        p2. add(2, 11);
        p2. add(3, 90);
        assertTrue(comp.compare(p2, p1) > 0);
    }

    @Test
    public void stepsCompDraw(){
        StepsManager p1 = new StepsManager();
        StepsManager p2 = new StepsManager();
        p1. add(1, 11);
        p1. add(2, 10);
        p1. add(365, 13);
        p2. add(1, 10);
        p2. add(20, 11);
        p2. add(31, 13);
        assertEquals(0, comp.compare(p2, p1));
    }

    @Test
    public void stepsCompNoOverflow(){
        StepsManager p1 = new StepsManager();
        StepsManager p2 = new StepsManager();
        StepsManagerComparator compMax = new StepsManagerComparator(Integer.MAX_VALUE);
        p1. add(1, Integer.MAX_VALUE);
        p1. add(365, 1);
        p1. add(1, Integer.MAX_VALUE);
        p2. add(1, 1);
        p2. add(365, Integer.MAX_VALUE);
        p2. add(1, Integer.MAX_VALUE);
        assertEquals(0, compMax.compare(p2, p1));
    }

    @Test
    public void stepsCompZero(){
        StepsManagerComparator compZero = new StepsManagerComparator(0);
        StepsManager p1 = new StepsManager();
        StepsManager p2 = new StepsManager();
        p1. add(1, 1);
        p1. add(2, 1);
        p1. add(3, 13);
        p2. add(1, 1);
        p2. add(2, 12);
        p2. add(3, 13);
        p2. add(4, 100);
        assertTrue(compZero.compare(p1, p2) < 0);
    }

    @Test
    public void stepsCompNegative(){
        StepsManagerComparator compNeg = new StepsManagerComparator(-100);
        StepsManager p1 = new StepsManager();
        StepsManager p2 = new StepsManager();
        p1. add(1, 1);
        p1. add(2, 1);
        p1. add(3, 13);
        p2. add(1, 1);
        p2. add(2, 12);
        p2. add(3, 13);
        p2. add(4, 100);
        assertTrue(compNeg.compare(p1, p2) < 0);
    }
}