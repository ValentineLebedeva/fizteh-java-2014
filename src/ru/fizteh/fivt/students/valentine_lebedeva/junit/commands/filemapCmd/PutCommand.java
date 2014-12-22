package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.filemapCmd;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.FileMapCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public final class PutCommand extends FileMapCommand {
    public PutCommand(int number) {
        super(number);
    }

    @Override
    public void execute(final String[] args, Object tables) {
        checkWorkTable((Provider) tables);
        checkArgs(args);
        if (((Provider) tables).getWorkTable().containsKey(args[0])) {
            System.out.println("overwrite");
            System.out.println(args[1]);
        } else {
            System.out.println("new");
        }
        ((Provider) tables).getWorkTable().put(args[0], args[1]);
    }
}
