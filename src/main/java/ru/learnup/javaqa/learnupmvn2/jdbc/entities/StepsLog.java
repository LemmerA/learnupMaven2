package ru.learnup.javaqa.learnupmvn2.jdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.learnup.javaqa.learnupmvn2.exception.IllegalDayException;
import ru.learnup.javaqa.learnupmvn2.exception.IllegalStepsException;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StepsLog {
    public int id;
    public int day;
    public int steps;
    public Timestamp dateCreate;
    public boolean isCommitted;

    public StepsLog (int day, int steps){
        if (steps <= 0){
            throw new IllegalStepsException(steps);
        }

        if (day < 1 || day > 365) {
            throw new IllegalDayException(day);
        }

        this.day = day;
        this.steps = steps;
        this.dateCreate = Timestamp.from(Instant.now());
        this.isCommitted = false;
    }

}
