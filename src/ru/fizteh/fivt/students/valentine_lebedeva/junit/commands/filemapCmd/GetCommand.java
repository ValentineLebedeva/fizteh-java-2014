package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.filemapCmd;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.Command;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public final class GetCommand extends Command {
    public GetCommand(int number) {
        super(number);
    }

    @Override
    public void execute(final String[] args, Object tables) {
        if (((Provider) tables).checkWorkTable()) {
            checkArgs(args);
            if (((Provider) tables).getWorkTable().containsKey(args[1])) {
                System.out.println("found");
                System.out.println(((Provider) tables).getWorkTable().get(args[1]));
            } else {
                System.out.println("not found");
            }
        } else {
            System.out.println("no table");
        }
    }
}
