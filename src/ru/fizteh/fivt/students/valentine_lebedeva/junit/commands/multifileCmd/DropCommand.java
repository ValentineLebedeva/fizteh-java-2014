package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.multifileCmd;

import java.io.IOException;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.Command;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public final class DropCommand extends Command {
    public DropCommand(int number) {
        super(number);
    }

    @Override
    public void execute(final String[] args, Object tables) throws IOException {
        checkArgs(args);
        ((Provider) tables).removeTable(args[0]);
        System.out.println("dropped");
    }
}
