package ru.fizteh.fivt.students.valentine_lebedeva.junit.tables;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ru.fizteh.fivt.students.valentine_lebedeva.junit.utils.Rm;

public class MultiFileTableTest {
    private File tableDir = new File(System.getProperty("test.dir"), "multiFileTableTest");

    @Before
    public void beforeTest() throws IOException {
        if (tableDir.exists() && tableDir.list().length != 0) {
            Rm.rmRec(tableDir.getAbsolutePath());
        }
        tableDir.mkdir();
    }

    @Test
    public void testRead() throws IOException {
        MultiFileTable table = new MultiFileTable(tableDir.getParent(), tableDir.getName());
        table.put("ky", "value");
        table.commit();
        Map<String, String> base = new HashMap<>();
        base.put("ky", "value");
        Assert.assertEquals(table.read(), base);
    }

    @Test
    public void testClose() throws IOException {
        MultiFileTable table = new MultiFileTable(tableDir.getParent(), tableDir.getName());
        table.put("jj", "value");
        table.commit();
        table.close();
    }

    @Test
    public void testRollback() throws IOException {
        MultiFileTable table = new MultiFileTable(tableDir.getParent(), tableDir.getName());
        table.put("key", "value");
        table.commit();
        table.put("key1", "value1");
        Assert.assertEquals(table.rollback(), 1);
    }

    @Test
    public void testRemoveKeyExists() throws IOException {
        MultiFileTable table = new MultiFileTable(tableDir.getParent(), tableDir.getName());
        table.put("key", "value");
        table.commit();
        Assert.assertNotEquals(table.remove("key"), null);
    }

    @Test
    public void testRemoveKeyNotExists() throws IOException {
        MultiFileTable table = new MultiFileTable(tableDir.getParent(), tableDir.getName());
        Assert.assertEquals(table.remove("key"), null);
    }
}
