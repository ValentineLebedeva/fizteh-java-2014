package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.filemapCmd;

import java.io.IOException;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.Command;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public class RemoveCommand extends Command {
    public RemoveCommand(int number) {
        super(number);
    }

    @Override
    public void execute(String[] args, Object tables) throws IOException {
        if (((Provider) tables).checkWorkTable()) {
            checkArgs(args);
            if (((Provider) tables).getWorkTable().containsKey(args[1])) {
                ((Provider) tables).getWorkTable().remove(args[1]);
                System.out.println("removed");
            } else {
                System.out.println("not found");
            }
        } else {
            System.out.println("no table");
        }
    }
}
