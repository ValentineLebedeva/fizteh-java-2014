package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.multifileCmd;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.Command;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public final class ShowCommand extends Command {
    public ShowCommand(int number) {
        super(number);
    }

    @Override
    public void execute(String[] args, Object tables) throws IOException {
        checkArgs(args);
        if (!args[0].equals("tables")) {
            throw new IllegalArgumentException("Wrong arguments");
        }
        Map<String, Integer> listTables = ((Provider) tables).showTables();
        for (Entry<String, Integer> entry : listTables.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}
