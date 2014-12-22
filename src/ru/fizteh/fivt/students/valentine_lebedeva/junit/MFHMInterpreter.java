package ru.fizteh.fivt.students.valentine_lebedeva.junit;

import ru.fizteh.fivt.storage.strings.TableProvider;
import ru.fizteh.fivt.storage.strings.TableProviderFactory;
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

public final class MFHMInterpreter extends Interpreter {
    public MFHMInterpreter() {
        super();
        commands.put("create", new CreateCommand(1));
        commands.put("drop", new DropCommand(1));
        commands.put("use", new UseCommand(1));
        commands.put("show", new ShowCommand(1));
        commands.put("list", new ListCommand(0));
        commands.put("put", new PutCommand(2));
        commands.put("get", new GetCommand(1));
        commands.put("remove", new RemoveCommand(1));
        commands.put("exit", new ExitCommand(0));
        commands.put("size", new SizeCommand(0));
        commands.put("commit", new CommitCommand(0));
        commands.put("rollback", new RollbackCommand(0));
    }

    @Override
    public Object getObject() throws Exception {
        TableProviderFactory newTables = new ProviderFactory();
        TableProvider tables = newTables.create(System.getProperty("fizteh.db.dir"));
        return tables;
    }
}
