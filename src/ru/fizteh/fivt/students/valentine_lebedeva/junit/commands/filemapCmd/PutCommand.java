package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.filemapCmd;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.Command;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public final class PutCommand extends Command {
    public PutCommand(int number) {
        super(number);
    }

    @Override
    public void execute(final String[] args, Object tables) {
        if (((Provider) tables).checkWorkTable()) {
            checkArgs(args);
            if (((Provider) tables).getWorkTable().containsKey(args[1])) {
                System.out.println("overwrite");
                System.out.println(args[2]);
            } else {
                System.out.println("new");
            }
            ((Provider) tables).getWorkTable().put(args[1], args[2]);
        } else {
            System.out.println("no table");
        }
    }
}
