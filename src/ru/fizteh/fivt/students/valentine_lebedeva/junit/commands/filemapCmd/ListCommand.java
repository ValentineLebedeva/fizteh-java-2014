package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.filemapCmd;

import java.util.List;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.FileMapCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public final class ListCommand extends FileMapCommand {
    public ListCommand(int number) {
        super(number);
    }

    @Override
    public void execute(final String[] args, Object tables) {
        checkWorkTable((Provider) tables);
        checkArgs(args);
        List<String> out = ((Provider) tables).getWorkTable().list();
        System.out.println(String.join(", ", out));
    }
}
