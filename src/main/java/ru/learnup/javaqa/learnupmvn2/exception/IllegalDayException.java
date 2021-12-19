package ru.learnup.javaqa.learnupmvn2.exception;

public class IllegalDayException extends IllegalArgumentException {

    public IllegalDayException(int arg) {
        super("Illegal day: " + arg + ". Should be between 1 and 365");
    }
}
