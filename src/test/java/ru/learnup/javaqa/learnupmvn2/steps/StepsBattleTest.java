package ru.learnup.javaqa.learnupmvn2.steps;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StepsBattleTest {

    StepsManager p1 = Mockito.mock(StepsManager.class);
    StepsManager p2 = Mockito.mock(StepsManager.class);

    //addSteps tests
    @Test
    public void addStepsP1(){
        StepsBattle stepsBattle = new StepsBattle(p1, p2);
        stepsBattle.addSteps(stepsBattle.getP1(), 1, 3);
        verify(p1).add(1,3);
    }

    @Test
    public void addStepsP2(){
        StepsBattle stepsBattle = new StepsBattle(p1, p2);
        stepsBattle.addSteps(stepsBattle.getP2(), 5, 123);
        verify(p2).add(5,123);
    }

    @Test
    public void addStepsWrongManager(){
        StepsBattle stepsBattle = new StepsBattle(p1, p2);
        StepsManager p3 = Mockito.mock(StepsManager.class);
        stepsBattle.addSteps(p3, 5, 123);
        verify(p3, never()).add(anyInt(), anyInt());
    }

    @Test
    public void addStepsNegativeDay(){
        StepsBattle stepsBattle = new StepsBattle(p1, p2);
        stepsBattle.addSteps(stepsBattle.getP1(), -1, 123);
        stepsBattle.addSteps(stepsBattle.getP2(), 0, 123);
        verify(p1, never()).add(anyInt(), anyInt());
        verify(p2, never()).add(anyInt(), anyInt());
    }

    @Test
    public void addStepsNegativeSteps(){
        StepsBattle stepsBattle = new StepsBattle(p1, p2);
        stepsBattle.addSteps(stepsBattle.getP1(), 1, -123);
        stepsBattle.addSteps(stepsBattle.getP2(), 3, 0);
        verify(p1, never()).add(anyInt(), anyInt());
        verify(p2, never()).add(anyInt(), anyInt());
    }

    //winner tests
    @Test
    public void winnerInit(){
        StepsBattle stepsBattle = new StepsBattle(p1, p2);
        assertEquals(0, stepsBattle.winner());
    }

    @Test
    public void winnerP1(){
        doReturn(Map.of(1, 10, 3, 1, 100, 2, 999, 999)).when(p1).getStats();
        doReturn(Map.of(1, 11, 2, 1, 99, 2)).when(p2).getStats();
        StepsBattle stepsBattle = new StepsBattle(p1, p2);
        assertEquals(1, stepsBattle.winner());
    }

    @Test
    public void winnerP2(){
        doReturn(Map.of(1, 10, 3, 1, 100, 2)).when(p1).getStats();
        doReturn(Map.of(1, 11, 2, 1, 99, 2)).when(p2).getStats();
        StepsBattle stepsBattle = new StepsBattle(p1, p2);
        assertEquals(2, stepsBattle.winner());
    }

    @Test
    public void winnerDraw(){
        doReturn(Map.of(1, 10, 3, 1, 100, 2, 999, 1)).when(p1).getStats();
        doReturn(Map.of(1, 11, 2, 1, 99, 2)).when(p2).getStats();
        StepsBattle stepsBattle = new StepsBattle(p1, p2);
        assertEquals(0, stepsBattle.winner());
    }

    @Test
    public void winnerNoOverflow(){
        doReturn(Map.of(1, Integer.MAX_VALUE, 3, Integer.MAX_VALUE, 100, Integer.MAX_VALUE,
                999, Integer.MAX_VALUE)).when(p1).getStats();
        doReturn(Map.of(1, Integer.MAX_VALUE, 99, Integer.MAX_VALUE, 2, 1)).when(p2).getStats();
        StepsBattle stepsBattle = new StepsBattle(p1, p2);
        assertEquals(1, stepsBattle.winner());
    }
}