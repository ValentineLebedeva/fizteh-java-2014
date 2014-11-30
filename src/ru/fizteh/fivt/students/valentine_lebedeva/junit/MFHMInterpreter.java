package ru.fizteh.fivt.students.valentine_lebedeva.junit;

import java.io.IOException;

import ru.fizteh.fivt.storage.strings.TableProvider;
import ru.fizteh.fivt.storage.strings.TableProviderFactory;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.Command;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.ExitCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.filemapCmd.GetCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.filemapCmd.ListCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.filemapCmd.PutCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.filemapCmd.RemoveCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.junitCmd.CommitCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.junitCmd.RollbackCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.junitCmd.SizeCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.multifileCmd.CreateCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.multifileCmd.DropCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.multifileCmd.ShowCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.commands.multifileCmd.UseCommand;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.interpreter.Interpreter;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.tables.ProviderFactory;

public class MFHMInterpreter extends Interpreter {
    public MFHMInterpreter() {
        super();
        commands.put("create", new CreateCommand(2));
        commands.put("drop", new DropCommand(2));
        commands.put("use", new UseCommand(2));
        commands.put("show", new ShowCommand(2));
        commands.put("list", new ListCommand(1));
        commands.put("put", new PutCommand(3));
        commands.put("get", new GetCommand(2));
        commands.put("remove", new RemoveCommand(2));
        commands.put("exit", new ExitCommand(1));
        commands.put("size", new SizeCommand(1));
        commands.put("commit", new CommitCommand(1));
        commands.put("rollback", new RollbackCommand(1));
    }

    @Override
    public Object getObject() throws Exception {
        TableProviderFactory newTables = new ProviderFactory();
        TableProvider tables = newTables.create(System.getProperty("fizteh.db.dir"));
        return tables;
    }

    @Override
    public void exit(Object object) throws IOException {
        Command exit = new ExitCommand(1);
        String[] tmp = { "exit" };
        exit.execute(tmp, object);
    }
}
