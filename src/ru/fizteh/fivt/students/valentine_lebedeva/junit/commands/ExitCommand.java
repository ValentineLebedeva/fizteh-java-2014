package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands;

import java.io.IOException;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.interpreter.StopInterpretationException;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public final class ExitCommand extends Command {
    public ExitCommand(int number) {
        super(number);
    }

    @Override
    public void execute(String[] args, Object tables) throws IOException {
        checkArgs(args);
        ((Provider) tables).close();
        throw new StopInterpretationException(2);
    }
}
