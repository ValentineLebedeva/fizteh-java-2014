package ru.fizteh.fivt.students.valentine_lebedeva.junit;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.interpreter.StopInterpretationException;
import ru.fizteh.fivt.students.valentine_lebedeva.junit.utils.RemoveFile;

public class MFHMInterpreterTest {
    private MFHMInterpreter interpreter = new MFHMInterpreter();

    @Before
    public void beforeTest() throws IOException {
        File providerDir = new File(System.getProperty("test.dir"), "providerTest");
        if (providerDir.exists()) {
            RemoveFile.rmRec(providerDir.getAbsolutePath());
        }
        providerDir.mkdir();
    }

    @Test(expected = StopInterpretationException.class)
    public void testBatchModeWrongCommand() throws Exception {
        String[] args = {"wrong"};
        interpreter.batch(args);
    }

    @Test(expected = StopInterpretationException.class)
    public void testBatchWrongNumberOfArguments() throws Exception {
        String[] args = {"create bla bla"};
        interpreter.batch(args);
    }

    @Test(expected = StopInterpretationException.class)
    public void testBatchMode() throws Exception {
        String[] args = {"create tab; use tab; put key value; size; get key; commit; remove key; rollback; drop"};
        interpreter.batch(args);
    }

    @Test(expected = StopInterpretationException.class)
    public void testInterpreterMode() throws Exception {
        String input = "create table; use table; put a b; list; show tables; commit; exit";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        interpreter.interactive();
    }
}
