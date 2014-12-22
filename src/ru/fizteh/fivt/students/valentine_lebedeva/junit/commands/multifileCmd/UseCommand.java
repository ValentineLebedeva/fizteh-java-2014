package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.multifileCmd;

import java.io.IOException;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.Command;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.MultiFileTable;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public final class UseCommand extends Command {
    public UseCommand(int number) {
        super(number);
    }

    @Override
    public void execute(String[] args, Object tables) throws IOException {
        checkArgs(args);
        if (((Provider) tables).containsKey(args[0])) {
            int changes;
            if ((changes = ((MultiFileTable) ((Provider) tables).getTable(args[0])).getChanges()) != 0) {
                throw new IllegalStateException(changes + " unsaved changes");
            }
            ((Provider) tables).setWorkTable(args[0]);
            System.out.println("using " + args[0]);
        } else {
            System.out.println(args[0] + " not exists");
        }
    }
}
