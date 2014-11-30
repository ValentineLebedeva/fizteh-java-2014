package ru.fizteh.fivt.students.valentine_lebedeva.junit.interpreter;

public class StopInterpretationException extends RuntimeException {
    private int exitMode;
    private static final long serialVersionUID = 1L;

    public StopInterpretationException(int status) {
        super();
        exitMode = status;
    }

    public int getExitMode() {
        return exitMode;
    }
}
