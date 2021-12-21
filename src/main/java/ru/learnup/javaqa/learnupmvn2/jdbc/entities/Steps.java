package ru.learnup.javaqa.learnupmvn2.jdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Steps {
    public int id;
    public int day;
    public int steps;
    public Timestamp dateCreate;
    public Timestamp dateUpdate;

}
