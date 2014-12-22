package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.multifileCmd;

import java.io.IOException;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.Command;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public final class CreateCommand extends Command {
    public CreateCommand(int number) {
        super(number);
    }

    @Override
    public void execute(final String[] args, Object tables) throws IOException {
        checkArgs(args);
        if (((Provider) tables).createTable(args[0]) == null) {
            System.out.println(args[0] + " exists");
        } else {
            System.out.println("created");
        }
    }
}
