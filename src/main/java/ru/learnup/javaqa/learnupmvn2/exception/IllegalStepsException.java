package ru.learnup.javaqa.learnupmvn2.exception;

public class IllegalStepsException extends IllegalArgumentException {
    public IllegalStepsException(int arg) {
        super("Illegal steps count: " + arg + ". Should be positive");
    }
}
