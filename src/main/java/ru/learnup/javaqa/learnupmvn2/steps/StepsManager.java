package ru.learnup.javaqa.learnupmvn2.steps;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Getter
@NoArgsConstructor
public class StepsManager implements Comparable<StepsManager>{
    private Map<Integer, Integer> stats = new HashMap<>();
    private int stepsRecord = 0;

    public void add(int day, int steps) {
        if (steps <= 0 || day <= 0){
            return;
        }

        Integer valueCheck = stats.putIfAbsent(day, steps);

        if (valueCheck == null) {
            if (steps > stepsRecord){
                stepsRecord = steps;
            }
        } else {
            try {
                valueCheck = Math.addExact(valueCheck, steps);
            } catch (ArithmeticException e) {
                valueCheck = Integer.MAX_VALUE;
            }
            stats.put(day, valueCheck);
            if (valueCheck > stepsRecord){
                stepsRecord = valueCheck;
            }
        }
    }

    public int remainder(int day) {
        if (day <= 0) {
            return -1;
        }

        try {
        return Math.addExact(stepsRecord - stats.getOrDefault(day, 0), 1);
        } catch (ArithmeticException e) {
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public int compareTo(StepsManager manager) {
        long counterP1 = 0;
        for(Map.Entry<Integer, Integer> day: this.getStats().entrySet()){
            counterP1 += day.getValue();
        }

        long counterP2 = 0;
        for(Map.Entry<Integer, Integer> day: manager.getStats().entrySet()){
            counterP2 += day.getValue();
        }

        return Long.compare(counterP1, counterP2);
    }

    public Stream<Integer> getAllAbove(int steps) {
        return this.getStats().entrySet()
                .stream()
                .filter(s -> s.getValue() > steps)
                .map(Map.Entry::getKey);
    }
}
