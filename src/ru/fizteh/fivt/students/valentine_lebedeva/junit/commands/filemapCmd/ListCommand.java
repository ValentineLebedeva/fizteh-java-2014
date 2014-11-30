package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.filemapCmd;

import java.util.List;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.Command;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public final class ListCommand extends Command {
    public ListCommand(int number) {
        super(number);
    }

    @Override
    public void execute(final String[] args, Object tables) {
        if (((Provider) tables).checkWorkTable()) {
            checkArgs(args);
            List<String> out = ((Provider) tables).getWorkTable().list();
            System.out.println(String.join(", ", out));
        } else {
            System.out.println("no table");
        }
    }
}
