package ru.learnup.javaqa.learnupmvn2.steps;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
public class StepsBattle {
    private StepsManager p1;
    private StepsManager p2;

    public void addSteps (StepsManager player, int day, int steps) {
        if (!(player == p1 || player == p2) || day <= 0 || steps <= 0) {
            return;
        }
        player.add(day, steps);
    }

    public int winner() {
        long counterP1 = 0;
        for(Map.Entry<Integer, Integer> day: p1.getStats().entrySet()){
            counterP1 += day.getValue();
        }
        long counterP2 = 0;
        for(Map.Entry<Integer, Integer> day: p2.getStats().entrySet()){
            counterP2 += day.getValue();
        }

        if (counterP1 > counterP2){
            return 1;
        } else if (counterP2 > counterP1){
            return 2;
        } else {
            return 0;
        }
    }
}
