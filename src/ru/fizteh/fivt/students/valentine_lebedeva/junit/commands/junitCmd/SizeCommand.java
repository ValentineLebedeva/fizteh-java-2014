package ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.junitCmd;

import java.io.IOException;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.Command;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.Provider;

public final class SizeCommand extends Command {
    public SizeCommand(int number) {
        super(number);
    }

    @Override
    public void execute(String[] args, Object tables) throws IOException {
        checkArgs(args);
        System.out.println(((Provider) tables).size());
    }
}
