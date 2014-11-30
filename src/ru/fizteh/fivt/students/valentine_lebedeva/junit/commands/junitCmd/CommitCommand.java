package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.junitCmd;

import java.io.IOException;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.Command;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public class CommitCommand extends Command {
    public CommitCommand(int number) {
        super(number);
    }

    @Override
    public void execute(String[] args, Object tables) throws IOException {
        checkArgs(args);
        if (((Provider) tables).checkWorkTable()) {
            System.out.println(((Provider) tables).getWorkTable().commit());
        } else {
            System.out.println("no table");
        }
    }
}
