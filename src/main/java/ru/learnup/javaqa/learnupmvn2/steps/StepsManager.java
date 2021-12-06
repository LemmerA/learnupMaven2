package ru.learnup.javaqa.learnupmvn2.steps;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class StepsManager {
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
            stats.put(day, valueCheck = Math.round(valueCheck + (float)steps));
            if (valueCheck > stepsRecord){
                stepsRecord = valueCheck;
            }
        }
    }

    public int remainder(int day) {
        if (day <= 0){
            return -1;
        }

        return Math.round(stepsRecord - stats.getOrDefault(day, 0) + 1.0f);
    }
}
