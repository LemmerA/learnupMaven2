package ru.learnup.javaqa.learnupmvn2.jdbc.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "steps")
public class Steps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "day")
    public int day;

    @Column(name = "steps")
    public int steps;

    @Column(name = "date_create")
    public Timestamp dateCreate;

    @Column(name = "date_update")
    public Timestamp dateUpdate;

}
