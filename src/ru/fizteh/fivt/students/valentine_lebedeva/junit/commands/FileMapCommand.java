package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public abstract class FileMapCommand extends Command {
    public FileMapCommand(int number) {
        super(number);
    }

    public final void checkWorkTable(final Provider provider) {
        if (!provider.checkWorkTable()) {
            throw new IllegalStateException("no table");
        }
    }
}
