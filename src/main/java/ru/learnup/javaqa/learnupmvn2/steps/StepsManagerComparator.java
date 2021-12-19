package ru.learnup.javaqa.learnupmvn2.steps;

import java.util.Comparator;
import java.util.Map;

public class StepsManagerComparator implements Comparator<StepsManager> {
    private int min = 0;

    public StepsManagerComparator(int min){
        if (min <= 0) {
            return;
        }
        this.min = min;
    }

    @Override
    public int compare(StepsManager p1, StepsManager p2) {
        long counterP1 = 0;
        for(Map.Entry<Integer, Integer> day: p1.getStats().entrySet()){
            if (day.getValue() > min) {
                counterP1 += 1;
            }
        }

        long counterP2 = 0;
        for(Map.Entry<Integer, Integer> day: p2.getStats().entrySet()){
            if (day.getValue() > min) {
                counterP2 += 1;
            }
        }

        return Long.compare(counterP1, counterP2);
    }
}
