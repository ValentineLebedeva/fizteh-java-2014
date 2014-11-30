package ru.fizteh.fivt.students.valentine_lebedeva.junit;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.interpreter.StopInterpretationException;

public final class Main {
    public static void main(final String[] args) throws Exception {
        MFHMInterpreter interpreter = new MFHMInterpreter();
        try {
            if (args.length == 0) {
                interpreter.interactive();
            } else {
                interpreter.batch(args);
            }
        } catch (StopInterpretationException e) {
            if (e.getExitMode() == 1) {
                System.exit(1);
            }
            if (e.getExitMode() == 2) {
                System.exit(0);
            }
        }
    }
}
