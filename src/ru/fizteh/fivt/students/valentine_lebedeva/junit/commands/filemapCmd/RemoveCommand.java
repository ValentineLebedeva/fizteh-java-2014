package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.filemapCmd;

import java.io.IOException;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.FileMapCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public class RemoveCommand extends FileMapCommand {
    public RemoveCommand(int number) {
        super(number);
    }

    @Override
    public void execute(String[] args, Object tables) throws IOException {
        checkWorkTable((Provider) tables);
        checkArgs(args);
        if (((Provider) tables).getWorkTable().containsKey(args[0])) {
            ((Provider) tables).getWorkTable().remove(args[0]);
            System.out.println("removed");
        } else {
            System.out.println("not found");
        }
    }
}
