package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.filemapCmd;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.FileMapCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public final class GetCommand extends FileMapCommand {
    public GetCommand(int number) {
        super(number);
    }

    @Override
    public void execute(final String[] args, Object tables) {
        checkWorkTable((Provider) tables);
        checkArgs(args);
        if (((Provider) tables).getWorkTable().containsKey(args[0])) {
            System.out.println("found");
            System.out.println(((Provider) tables).getWorkTable().get(args[0]));
        } else {
            System.out.println("not found");
        }
    }
}
