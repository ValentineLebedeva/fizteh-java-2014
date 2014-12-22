package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands;

import java.io.IOException;

public abstract class Command {
    protected int numberOfArgs;

    public Command(int number) {
        numberOfArgs = number;
    }

    public abstract void execute(String[] args, Object manager) throws IOException;

    public final void checkArgs(final String[] args) {
        if (args.length != numberOfArgs) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
    }
}
