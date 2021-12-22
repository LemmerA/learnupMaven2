package ru.learnup.javaqa.learnupmvn2.jdbc.entities;

import lombok.*;
import ru.learnup.javaqa.learnupmvn2.exception.IllegalDayException;
import ru.learnup.javaqa.learnupmvn2.exception.IllegalStepsException;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "steps_log")
public class StepsLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "day")
    public int day;

    @Column(name = "steps")
    public int steps;

    @Column(name = "date_create")
    public Timestamp dateCreate;

    @Column(name = "is_committed")
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
